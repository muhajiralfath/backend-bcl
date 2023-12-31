package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Bill;
import com.xfour.businesscapitalloan.entity.Provision;
import com.xfour.businesscapitalloan.entity.Submission;
import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.model.request.NewSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.SearchSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.UpdateSubmissionRequest;
import com.xfour.businesscapitalloan.model.response.SubmissionResponse;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import com.xfour.businesscapitalloan.repository.SubmissionRepository;
import com.xfour.businesscapitalloan.service.BillService;
import com.xfour.businesscapitalloan.service.ProvisionService;
import com.xfour.businesscapitalloan.service.SubmissionService;
import com.xfour.businesscapitalloan.service.UmkmService;
import com.xfour.businesscapitalloan.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final UmkmService umkmService;
    private final ProvisionService provisionService;
    private final BillService billService;
    private final ValidationUtil validationUtil;

    @Override
    public Submission findById(String  id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "submission not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SubmissionResponse create(NewSubmissionRequest request) {
        log.info("start of create submission");
        validationUtil.validate(request);
        Umkm umkm = umkmService.findById(request.getUmkmId());
        if (umkm.getUmkmType().equals("mikro") && (request.getLoanAmount() < 1_000_000 || request.getLoanAmount() > 3_000_000)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan amount not valid, type mikro must be 1.000.000 - 3.000.000");
        }
        if (umkm.getUmkmType().equals("kecil") && (request.getLoanAmount() < 5_000_000 || request.getLoanAmount() > 20_000_000)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan amount not valid, type kecil must be 5.000.000 - 20.000.000");
        }
        if (umkm.getUmkmType().equals("menengah") && (request.getLoanAmount() < 10_000_000 || request.getLoanAmount() > 50_000_000)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan amount not valid, type menengah must be 10.000.000 - 50.000.000");
        }

        Submission submission = Submission.builder()
                .tenor(request.getTenor())
                .loanAmount(request.getLoanAmount())
                .umkm(umkm)
                .build();
        submissionRepository.saveAndFlush(submission);
        log.info("end of create submission");

        return toSubmissionResponse(submission);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SubmissionResponse> getAll(SearchSubmissionRequest request) {
        log.info("start of getAll submission");
        Specification<Submission> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(request.getMinLoanAmount())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("loanAmount"), request.getMinLoanAmount()));
            }

            if (Objects.nonNull(request.getMaxLoanAmount())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("loanAmount"), request.getMaxLoanAmount()));
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Submission> submissions = submissionRepository.findAll(specification, pageable);

        List<SubmissionResponse> submissionResponses = new ArrayList<>();
        for (Submission submission: submissions.getContent()) {
            SubmissionResponse submissionResponse = toSubmissionResponse(submission);
            submissionResponses.add(submissionResponse);
        }
        log.info("end of getAll submission");

        return new PageImpl<>(submissionResponses, pageable, submissions.getTotalElements());
    }

    @Override
    public List<SubmissionResponse> getAllByDebtorId(String id) {
        log.info("Start get all submission by debtor id");
        UmkmResponse umkm = umkmService.getByDebtorId(id);

        List<Submission> allByUmkmId = submissionRepository.findAllByUmkmId(umkm.getUmkmId());
        List<SubmissionResponse> submissionResponses = new ArrayList<>();

        for (Submission submission : allByUmkmId) {
            SubmissionResponse submissionResponse = toSubmissionResponse(submission);
            submissionResponses.add(submissionResponse);
        }
        log.info("Finish get all submission by debtor id");
        return submissionResponses;
    }

    @Transactional(readOnly = true)
    @Override
    public SubmissionResponse getById(String id) {
        log.info("start of get submission by id");
        Submission submission = findById(id);
        log.info("end of get submission by id");

        return toSubmissionResponse(submission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SubmissionResponse update(UpdateSubmissionRequest request) {
        log.info("start of update submission");
        Submission submission = findById(request.getId());
        submission.setIsApprove(request.getIsApprove());
        submissionRepository.save(submission);

        if (submission.getIsApprove()) {
            Provision provision = createProvision(submission.getId());
            provisionService.create(provision);

            createBills(provision.getId());
        }
        log.info("end of update submission");

        return toSubmissionResponse(submission);
    }



    private Provision createProvision(String submissionId) {
        Submission submission = findById(submissionId);
        return Provision.builder()
                .submission(submission)
                .build();
    }

    private void createBills(String provisionId) {
        Provision provision = provisionService.findById(provisionId);
        Long debt = 0L;

        if (provision.getSubmission().getTenor() == 3) {
            debt = (provision.getSubmission().getLoanAmount() + (provision.getSubmission().getLoanAmount() * 5/100)) / provision.getSubmission().getTenor();

            for (int i = 0; i < provision.getSubmission().getTenor(); i++) {
                LocalDate dueDate = provision.getCreatedAt().plusMonths(i + 1).toLocalDate();
                Instant instant = dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
                long dueDateEpochTime = instant.toEpochMilli();
                long fineDate = dueDateEpochTime + 86400 * 1000;

                Bill bill = Bill.builder()
                        .debt(debt)
                        .interest(5)
                        .dueDate(dueDate)
                        .fineDate(fineDate)
                        .isPaid(false)
                        .isVerify(false)
                        .provision(provision)
                        .umkm(provision.getSubmission().getUmkm())
                        .build();
                billService.create(bill);
            }
        }

        if (provision.getSubmission().getTenor() == 6) {
            debt = (provision.getSubmission().getLoanAmount() + (provision.getSubmission().getLoanAmount() * 8/100)) / provision.getSubmission().getTenor();

            for (int i = 0; i < provision.getSubmission().getTenor(); i++) {
                LocalDate dueDate = provision.getCreatedAt().plusMonths(i + 1).toLocalDate();
                Instant instant = dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
                long dueDateEpochTime = instant.toEpochMilli();
                long fineDate = dueDateEpochTime + 86400 * 1000;
                Bill bill = Bill.builder()
                        .debt(debt)
                        .interest(8)
                        .dueDate(dueDate)
                        .fineDate(fineDate)
                        .isPaid(false)
                        .isVerify(false)
                        .provision(provision)
                        .umkm(provision.getSubmission().getUmkm())
                        .build();
                billService.create(bill);
            }
        }

        if (provision.getSubmission().getTenor() == 12) {
            debt = (provision.getSubmission().getLoanAmount() + (provision.getSubmission().getLoanAmount() * 12/100)) / provision.getSubmission().getTenor();

            for (int i = 0; i < provision.getSubmission().getTenor(); i++) {
                LocalDate dueDate = provision.getCreatedAt().plusMonths(i + 1).toLocalDate();
                Instant instant = dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
                long dueDateEpochTime = instant.toEpochMilli();
                long fineDate = dueDateEpochTime + 86400 * 1000;
                Bill bill = Bill.builder()
                        .debt(debt)
                        .interest(12)
                        .dueDate(dueDate)
                        .fineDate(fineDate)
                        .isPaid(false)
                        .isVerify(false)
                        .provision(provision)
                        .umkm(provision.getSubmission().getUmkm())
                        .build();
                billService.create(bill);
            }
        }
    }

    private SubmissionResponse toSubmissionResponse(Submission submission) {
        log.info("start toSubmissionResponse");
        Long debt = 0L;
        if (submission.getTenor() == 3) {
            debt = submission.getLoanAmount() + (submission.getLoanAmount() * 5/100);
        }
        if (submission.getTenor() == 6) {
            debt = submission.getLoanAmount() + (submission.getLoanAmount() * 8/100);
        }
        if (submission.getTenor() == 12) {
            debt = submission.getLoanAmount() + (submission.getLoanAmount() * 12/100);
        }

        if (debt == 0L) throw new RuntimeException("error at debt");

        Long monthlyDebt = debt / submission.getTenor();
        log.info("end toSubmissionResponse");

        return SubmissionResponse.builder()
                .id(submission.getId())
                .debtorId(submission.getUmkm().getDebtor().getId())
                .umkmId(submission.getUmkm().getId())
                .umkmName(submission.getUmkm().getName())
                .date(submission.getCreatedAt())
                .loanAmount(submission.getLoanAmount())
                .tenor(submission.getTenor())
                .debt(debt)
                .monthlyDebt(monthlyDebt)
                .isApprove(submission.getIsApprove())
                .build();
    }


}
