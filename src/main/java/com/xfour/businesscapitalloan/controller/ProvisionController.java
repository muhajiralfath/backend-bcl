package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.SearchProvisionRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.PagingResponse;
import com.xfour.businesscapitalloan.model.response.ProvisionResponse;
import com.xfour.businesscapitalloan.service.ProvisionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/provisions")
@RequiredArgsConstructor
@Slf4j
public class ProvisionController {
    private final ProvisionService provisionService;

    @Operation(summary = "Get Provision By Id")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info("start getProvisionById");
        ProvisionResponse provisionResponse = provisionService.getById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(provisionResponse)
                .build();
        log.info("end getProvisionById");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @Operation(summary = "Get All Provision")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("start getAllProvision");
        SearchProvisionRequest request = SearchProvisionRequest.builder()
                .page(page)
                .size(size)
                .build();
        Page<ProvisionResponse> provisionResponses = provisionService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .count(provisionResponses.getTotalElements())
                .totalPages(provisionResponses.getTotalPages())
                .page(page)
                .size(size)
                .build();
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(provisionResponses.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }
}
