package com.xfour.busniesscapitalloan.controller;

import com.xfour.busniesscapitalloan.model.request.SubmissionRequest;
import com.xfour.busniesscapitalloan.model.response.CommonResponse;
import com.xfour.busniesscapitalloan.model.response.SubmissionResponse;
import com.xfour.busniesscapitalloan.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DEBTOR')")
    public ResponseEntity<?> create(
            @RequestBody SubmissionRequest request)
    {
        log.info("start create submission");
        SubmissionResponse submissionResponse = submissionService.create(request);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .data(submissionResponse)
                .build();
        log.info("end create submission");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(
            @RequestBody SubmissionRequest request)
    {
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
