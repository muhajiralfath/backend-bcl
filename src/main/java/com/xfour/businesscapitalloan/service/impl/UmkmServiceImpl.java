package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Debtor;
import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.entity.UserDetailsImpl;
import com.xfour.businesscapitalloan.model.request.NewUmkmRequest;
import com.xfour.businesscapitalloan.model.request.SearchUmkmRequest;
import com.xfour.businesscapitalloan.model.request.UpdateUmkmRequest;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import com.xfour.businesscapitalloan.repository.UmkmRepository;
import com.xfour.businesscapitalloan.service.DebtorService;
import com.xfour.businesscapitalloan.service.RoleService;
import com.xfour.businesscapitalloan.service.UmkmService;
import com.xfour.businesscapitalloan.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UmkmServiceImpl implements UmkmService {
    private final UmkmRepository umkmRepository;
    private final DebtorService debtorService;
    private final RoleService roleService;
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
        log.info("start getAllUmkm");
        Specification<Umkm> specification = (root, query, criteriaBuilder) -> {
            if (Objects.nonNull(request.getKeyword())) {
                Predicate predicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getKeyword().toLowerCase() + "%"),
                        criteriaBuilder.like(root.get("mobilePhone"), request.getKeyword().toLowerCase() + "%")
                );
                return query.where(predicate).getRestriction();
            }

            return query.getRestriction();
        };


        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Umkm> umkms = umkmRepository.findAll(specification, pageable);
        Page<UmkmResponse> umkmResponses = umkms.map(this::toUmkmResponse);
        log.info("end getAllUmkm");
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

    private UmkmResponse toUmkmResponse(Umkm umkm){
        return UmkmResponse.builder()
                .umkmId(umkm.getId())
                .noSiup(umkm.getNoSiup())
                .umkmName(umkm.getName())
                .address(umkm.getAddress())
                .capital(umkm.getCapital())
                .umkmType(umkm.getUmkmType())
                .bankAccount(umkm.getBankAccount())
                .build();
    }
}
