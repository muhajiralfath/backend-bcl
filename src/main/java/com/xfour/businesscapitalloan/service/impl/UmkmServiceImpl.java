package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Debtor;
import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.entity.UmkmDocument;
import com.xfour.businesscapitalloan.entity.UserDetailsImpl;
import com.xfour.businesscapitalloan.model.request.NewUmkmRequest;
import com.xfour.businesscapitalloan.model.request.SearchUmkmRequest;
import com.xfour.businesscapitalloan.model.request.UpdateUmkmRequest;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import com.xfour.businesscapitalloan.repository.UmkmRepository;
import com.xfour.businesscapitalloan.service.DebtorService;
import com.xfour.businesscapitalloan.service.UmkmDocumentService;
import com.xfour.businesscapitalloan.service.UmkmService;
import com.xfour.businesscapitalloan.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UmkmServiceImpl implements UmkmService {
    private final UmkmRepository umkmRepository;
    private final DebtorService debtorService;
    private final UmkmDocumentService documentService;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Umkm findById(String id) {
        return findByIdOrThrownNotFound(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UmkmResponse create(NewUmkmRequest request) {
        try {
            log.info("start create umkm");
            validationUtil.validate(request);

            Debtor debtor = debtorService.get(request.getDebtorId());
            Umkm umkm = Umkm.builder()
                    .name(request.getUmkmName())
                    .noSiup(request.getNoSiup())
                    .address(request.getAddress())
                    .capital(request.getCapital())
                    .umkmType(request.getUmkmType())
                    .bankAccount(request.getBankAccount())
                    .debtor(debtor)
                    .build();

            umkmRepository.saveAndFlush(umkm);

            log.info("end create umkm");
            return toUmkmResponse(umkm);
        } catch (DataIntegrityViolationException exception){
            log.error("duplicate umkm");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "umkm already created, can not make umkm again ");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UmkmResponse getById(String id) {
        log.info("start get UmkmResponse by id");
        Umkm umkm = findByIdOrThrownNotFound(id);
        log.info("end get UmkmResponse by id");
        return toUmkmResponse(umkm);
    }

    @Override
    public UmkmResponse getByDebtorId(String debtorId) {
        Umkm umkm = umkmRepository.findFirstByDebtor_Id(debtorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Umkm not found"));
        return toUmkmResponse(umkm);
    }

    @Override
    public UmkmResponse getByAuthentication(Authentication authentication) {
        log.info("start getUmkmById");
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Umkm umkm = umkmRepository.findFirstByDebtor_UserCredential_Id(principal.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "umkm not found"));
        log.info("end getUmkmById");
        return toUmkmResponse(umkm);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UmkmResponse> getAll(SearchUmkmRequest request) {
        log.info("Start getAllUmkm");

        Specification<Umkm> specification = buildSpecification(request);

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Umkm> umkms = umkmRepository.findAll(specification, pageable);
        Page<UmkmResponse> umkmResponses = umkms.map(this::toUmkmResponse);

        log.info("End getAllUmkm");
        return umkmResponses;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UmkmResponse update(UpdateUmkmRequest request) {
        log.info("start update umkm");
        validationUtil.validate(request);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UmkmResponse umkmResponse = getByAuthentication(authentication);

        if (!umkmResponse.getUmkmId().equals(request.getUmkmId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "forbidden to access this resource");

        Umkm umkm = findByIdOrThrownNotFound(request.getUmkmId());
        umkm.setName(request.getUmkmName());
        umkm.setNoSiup(request.getNoSiup());
        umkm.setAddress(request.getAddress());
        umkm.setCapital(request.getCapital());
        umkm.setUmkmType(request.getUmkmType());
        umkm.setBankAccount(request.getBankAccount());
        umkmRepository.save(umkm);

        log.info("end update umkm");
        return toUmkmResponse(umkm);
    }

    public Umkm findByIdOrThrownNotFound(String id){
        log.info("start find store by id & thrown error if not found");
        Umkm umkm = umkmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Umkm Not Found"));

        log.info("end find umkm by id");
        return umkm;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UmkmDocument uploadDocument(Authentication authentication, MultipartFile multipartFile) {
        UmkmResponse umkmResponse = getByAuthentication(authentication);
        Umkm umkm = umkmRepository.findById(umkmResponse.getUmkmId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "umkm not found"));

        return documentService.create(umkm, multipartFile);
    }

    @Override
    @Transactional(readOnly = true)
    public Resource downloadDocument(Authentication authentication) {
        UmkmResponse umkmResponse = getByAuthentication(authentication);
        UmkmDocument umkmDocument = documentService.getByUmkmId(umkmResponse.getUmkmId());
        return documentService.downloadDoc(umkmDocument.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Resource downloadDocumentById(String id) {
        return documentService.downloadDoc(id);
    }

    private UmkmResponse toUmkmResponse(Umkm umkm){
        String documentId = null;

        UmkmDocument document = umkm.getDocument();
        if (document != null) {
            documentId = document.getId();
        }

        return UmkmResponse.builder()
                .umkmId(umkm.getId())
                .noSiup(umkm.getNoSiup())
                .umkmName(umkm.getName())
                .address(umkm.getAddress())
                .capital(umkm.getCapital())
                .umkmType(umkm.getUmkmType())
                .bankAccount(umkm.getBankAccount())
                .documentId(documentId)
                .build();
    }

    private Specification<Umkm> buildSpecification(SearchUmkmRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(request.getKeyword())) {
                String keyword = "%" + request.getKeyword().toLowerCase() + "%";
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), keyword);
                Predicate idPredicate = criteriaBuilder.like(root.get("id"), keyword);

                predicates.add(criteriaBuilder.or(namePredicate, idPredicate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
