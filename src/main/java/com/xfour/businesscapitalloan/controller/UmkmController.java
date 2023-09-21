package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.entity.UmkmDocument;
import com.xfour.businesscapitalloan.model.request.NewUmkmRequest;
import com.xfour.businesscapitalloan.model.request.SearchUmkmRequest;
import com.xfour.businesscapitalloan.model.request.UpdateUmkmRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.PagingResponse;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import com.xfour.businesscapitalloan.service.UmkmService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/umkm")
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

    @Operation(summary = "Get Umkm By Debtor Id")
    @GetMapping(
            path = "/debtorId/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getByDebtorId(@PathVariable String id) {
        log.info("start getUmkmByDebtorId");
        UmkmResponse umkmResponse = umkmService.getByDebtorId(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(umkmResponse)
                .build();
        log.info("end getUmkmByDebtorId");

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

    @Operation(summary = "Upload UMKM Document")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('DEBTOR')")
    @PostMapping(path = "/upload-document")
    public ResponseEntity<?> uploadDocument(
            Authentication authentication,
            @RequestPart(name = "document") MultipartFile multipartFile
    ) {
        UmkmDocument umkmDocument = umkmService.uploadDocument(authentication, multipartFile);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(umkmDocument)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @Operation(summary = "Download UMKM Document")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "/download-document")
    public ResponseEntity<?> downloadDocument(Authentication authentication) {
        Resource resource = umkmService.downloadDocument(authentication);
        String contentType = determineContentType(Objects.requireNonNull(resource.getFilename()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @Operation(summary = "Download UMKM Document By Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "/download-umkm-document/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> downloadDocumentById(@PathVariable String id) {
        Resource resource = umkmService.downloadDocumentById(id);
        String contentType = determineContentType(Objects.requireNonNull(resource.getFilename()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    private String determineContentType(String filename) {
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        switch (extension) {
            case "png":
                return MimeTypeUtils.IMAGE_PNG_VALUE;
            case "jpg":
            case "jpeg":
                return MimeTypeUtils.IMAGE_JPEG_VALUE;
            case "pdf":
                return MediaType.APPLICATION_PDF_VALUE;
            default:
                return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }
}
