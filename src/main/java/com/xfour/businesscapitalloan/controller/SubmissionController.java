package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.NewSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.SearchSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.UpdateSubmissionRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.PagingResponse;
import com.xfour.businesscapitalloan.model.response.SubmissionResponse;
import com.xfour.businesscapitalloan.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/submissions")
@RequiredArgsConstructor
@Slf4j
public class SubmissionController {
    private final SubmissionService submissionService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create New Submission")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DEBTOR')")
    public ResponseEntity<?> create(
            @RequestBody NewSubmissionRequest request
    ) {
        log.info("start create submission");
        SubmissionResponse submissionResponse = submissionService.create(request);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .data(submissionResponse)
                .build();
        log.info("end create submission");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @Operation(summary = "Get Submission By Id")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info("start of getSubmissionById");
        SubmissionResponse submissionResponse = submissionService.getById(id);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .data(submissionResponse)
                .build();
        log.info("end of getSubmissionById");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get All Submission")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "minLoanAmount", required = false) Long minLoanAmount,
            @RequestParam(name = "maxLoanAmount", required = false) Long maxLoanAmount,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("start of getAllSubmission");
        SearchSubmissionRequest request = SearchSubmissionRequest.builder()
                .minLoanAmount(minLoanAmount)
                .maxLoanAmount(maxLoanAmount)
                .page(page)
                .size(size)
                .build();
        Page<SubmissionResponse> submissionResponses = submissionService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .count(submissionResponses.getTotalElements())
                .totalPages(submissionResponses.getTotalPages())
                .page(page)
                .size(size)
                .build();
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(submissionResponses.getContent())
                .paging(pagingResponse)
                .build();
        log.info("end of getAllSubmission");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Submission Approval")
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(
            @RequestBody UpdateSubmissionRequest request
    ) {
        log.info("start of update submission");
        SubmissionResponse submissionResponse = submissionService.update(request);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .data(submissionResponse)
                .build();
        log.info("end of update submission");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }
}
