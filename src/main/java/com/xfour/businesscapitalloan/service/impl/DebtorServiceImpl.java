package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Debtor;
import com.xfour.businesscapitalloan.entity.UserDetailsImpl;
import com.xfour.businesscapitalloan.model.request.SearchDebtorRequest;
import com.xfour.businesscapitalloan.model.request.UpdateDebtorRequest;
import com.xfour.businesscapitalloan.model.response.DebtorResponse;
import com.xfour.businesscapitalloan.repository.DebtorRepository;
import com.xfour.businesscapitalloan.service.DebtorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class DebtorServiceImpl implements DebtorService {
    private final DebtorRepository debtorRepository;
    @Override
    public Debtor create(Debtor debtor) {
        log.info("start create debtor");
        Debtor save = debtorRepository.save(debtor);
        log.info("end create debtor");
        return save;
    }

    @Override
    public Debtor get(String id) {
        return findByIdOrThrownNotFound(id);
    }

    @Override
    public DebtorResponse getByAuthentication(Authentication authentication) {
        log.info("start getByAuthentication");
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Debtor debtor = debtorRepository.findFirstByUserCredential_Id(principal.getUserId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        log.info("end getByAuthentication");
        return toDebtorResponse(debtor);
    }

    @Override
    public DebtorResponse getById(String id) {
        log.info("start getDebtorById");
        Debtor debtor = findByIdOrThrownNotFound(id);
        log.info("end getDebtorById");
        return toDebtorResponse(debtor);
    }

    @Override
    public Page<DebtorResponse> getAll(SearchDebtorRequest request) {
        Specification<Debtor> specification = (root, query, criteriaBuilder) -> {
            if (Objects.nonNull(request.getKeyword())) {
                Predicate predicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getKeyword().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), request.getKeyword().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("handphone")), request.getKeyword() + "%")
                );
                return query.where(predicate).getRestriction();
            }

            return query.where().getRestriction();
        };
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Debtor> debtorPage = debtorRepository.findAll(specification, pageable);
        return debtorPage.map(DebtorServiceImpl::toDebtorResponse);
    }

    @Override
    public DebtorResponse update(UpdateDebtorRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DebtorResponse debtorResponse = getByAuthentication(authentication);
        if (!debtorResponse.getDebtorId().equals(request.getDebtorId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "do not access to this resource");
        }
        Debtor debtor = findByIdOrThrownNotFound(debtorResponse.getDebtorId());
        debtor.setNik(request.getNik());
        debtor.setNpwp(request.getNpwp());
        debtor.setName(request.getName());
        debtor.setHandphone(request.getHandphone());
        debtor.setBirthPlace(request.getBirthPlace());
        debtor.setBirthDate(request.getBirthDate());
        debtor.setGender(request.getGender());
        debtor.setStatus(request.getStatus());
        debtor.setAddress(request.getAddress());
        debtor.setJob(request.getJob());

        debtorRepository.save(debtor);
        return toDebtorResponse(debtor);
    }

    private Debtor findByIdOrThrownNotFound(String id){
        return debtorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "debtor not found"));
    }

    private static DebtorResponse toDebtorResponse(Debtor debtor){
        return DebtorResponse.builder()
                .debtorId(debtor.getId())
                .nik(debtor.getNik())
                .npwp(debtor.getNpwp())
                .name(debtor.getName())
                .handphone(debtor.getHandphone())
                .birthPlace(debtor.getBirthPlace())
                .birthDate(debtor.getBirthDate())
                .gender(debtor.getGender())
                .status(debtor.getStatus())
                .address(debtor.getAddress())
                .job(debtor.getJob())
                .email(debtor.getUserCredential().getEmail())
                .umkmId(debtor.getUmkm().getId())
                .umkmName(debtor.getUmkm().getName())
                .build();
    }
}
