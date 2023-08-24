package com.xfour.busniesscapitalloan.controller;

import com.xfour.busniesscapitalloan.model.request.NewUmkmRequest;
import com.xfour.busniesscapitalloan.model.request.SearchUmkmRequest;
import com.xfour.busniesscapitalloan.model.request.UpdateUmkmRequest;
import com.xfour.busniesscapitalloan.model.response.CommonResponse;
import com.xfour.busniesscapitalloan.model.response.PagingResponse;
import com.xfour.busniesscapitalloan.model.response.UmkmResponse;
import com.xfour.busniesscapitalloan.service.UmkmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/umkms")
@Slf4j
@Tag(name = "Umkm", description = "UMKM Management APIs")
public class UmkmController {
    private final UmkmService umkmService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create New Umkm")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('DEBTOR')")
    public ResponseEntity<?> create(@RequestBody NewUmkmRequest request) {
        log.info("start createNewUmkm");
        UmkmResponse umkmResponse = umkmService.create(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(umkmResponse)
                .build();
        log.info("end createNewUmkm");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @Operation(summary = "Get Umkm By Id")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info("start getUmkmById");
        UmkmResponse umkmResponse = umkmService.getById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(umkmResponse)
                .build();
        log.info("end getUmkmById");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get All Umkm")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAll(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("start getAllUmkm");
        SearchUmkmRequest request = SearchUmkmRequest.builder()
                .keyword(keyword)
                .page(page)
                .size(size)
                .build();

        Page<UmkmResponse> umkmResponses = umkmService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .count(umkmResponses.getTotalElements())
                .totalPages(umkmResponses.getTotalPages())
                .page(page)
                .size(size)
                .build();

        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(umkmResponses.getContent())
                .paging(pagingResponse)
                .build();

        log.info("end getAllUmkm");
        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PreAuthorize("hasRole('DEBTOR')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update Umkm")
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> update(@RequestBody UpdateUmkmRequest request) {
        log.info("start updateUmkm");
        UmkmResponse umkmResponse = umkmService.update(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(umkmResponse)
                .build();
        log.info("end updateUmkm");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }
}
