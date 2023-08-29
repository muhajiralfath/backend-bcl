package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.File;
import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.entity.UmkmDocument;
import com.xfour.businesscapitalloan.model.response.DebtorResponse;
import com.xfour.businesscapitalloan.repository.UmkmDocumentRepository;
import com.xfour.businesscapitalloan.service.DebtorService;
import com.xfour.businesscapitalloan.service.FileService;
import com.xfour.businesscapitalloan.service.UmkmDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UmkmDocumentServiceImpl implements UmkmDocumentService {
    private final UmkmDocumentRepository repository;
    private final FileService fileService;
    private final DebtorService debtorService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UmkmDocument create(Umkm umkm, MultipartFile multipartFile) {
        log.info("start createUmkmDocument");
        File file = fileService.create(multipartFile);
        log.info("End createUmkmDocument");
        UmkmDocument umkmDocument = UmkmDocument.builder()
                .name(file.getName())
                .contentType(file.getContentType())
                .path(file.getPath())
                .size(file.getSize())
                .umkm(umkm)
                .build();
        repository.save(umkmDocument);

        return umkmDocument;
    }

    @Override
    public UmkmDocument getById(String id) {
        log.info("start getUmkmDocumentById");
        UmkmDocument document = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "document not found"));
        log.info("end getUmkmDocumentById");
        return document;
    }

    @Override
    public UmkmDocument getByUmkmId(String umkmId) {
        log.info("start getUmkmDocumentByUmkmId");
        log.info(umkmId);
        UmkmDocument umkmDocument = repository.findUmkmDocumentByUmkmId(umkmId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "document not found"));
        log.info("end getUmkmDocumentByUmkmId");
        return umkmDocument;
    }

    @Override
    public Resource downloadDoc(String id) {
        log.info("start downloadDoc");
        UmkmDocument umkmDocument = getById(id);
        log.info("end downloadDoc");
        return fileService.get(umkmDocument.getPath());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        log.info("start deleteDocumentById");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DebtorResponse debtor = debtorService.getByAuthentication(authentication);
        UmkmDocument umkmDocument = getById(id);

        if (!umkmDocument.getUmkm().getDebtor().getId().equals(debtor.getDebtorId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "not allowed to access this resource");

        fileService.delete(umkmDocument.getPath());
        log.info("end deleteDocumentByid");
        repository.delete(umkmDocument);
    }
}
