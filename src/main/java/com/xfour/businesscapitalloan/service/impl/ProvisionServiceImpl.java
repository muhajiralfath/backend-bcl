package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Provision;
import com.xfour.businesscapitalloan.model.request.SearchProvisionRequest;
import com.xfour.businesscapitalloan.model.response.ProvisionResponse;
import com.xfour.businesscapitalloan.repository.ProvisionRepository;
import com.xfour.businesscapitalloan.service.ProvisionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProvisionServiceImpl implements ProvisionService {
    private final ProvisionRepository provisionRepository;

    @Override
    public Provision findById(String id) {
        return provisionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "provision not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProvisionResponse create(Provision provision) {
        log.info("start of create provision");
        Provision save = provisionRepository.save(provision);
        log.info("end of create provision");

        return toProvisionResponse(save);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProvisionResponse> getAll(SearchProvisionRequest request) {
        log.info("start of getAllProvision");
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Provision> provisions = provisionRepository.findAll(pageable);

        List<ProvisionResponse> provisionResponses = new ArrayList<>();
        for (Provision provision: provisions.getContent()) {
            provisionResponses.add(toProvisionResponse(provision));
        }
        log.info("end of getAllProvision");

        return new PageImpl<>(provisionResponses, pageable, provisions.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public ProvisionResponse getById(String id) {
        log.info("start of getByIdProvision");
        Provision provision = findById(id);
        log.info("end of getByIdProvision");

        return toProvisionResponse(provision);
    }

    private ProvisionResponse toProvisionResponse(Provision provision) {
        return ProvisionResponse.builder()
                .id(provision.getId())
                .submissionId(provision.getSubmission().getId())
                .umkmName(provision.getSubmission().getUmkm().getName())
                .debtorName(provision.getSubmission().getUmkm().getDebtor().getName())
                .bankAccount(provision.getSubmission().getUmkm().getBankAccount())
                .amount(provision.getSubmission().getLoanAmount())
                .time(provision.getCreatedAt())
                .build();
    }
}
