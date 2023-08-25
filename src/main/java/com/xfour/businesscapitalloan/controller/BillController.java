package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.SearchBillRequest;
import com.xfour.businesscapitalloan.model.request.UpdateBillAdminRequest;
import com.xfour.businesscapitalloan.model.request.UpdateBillUmkmRequest;
import com.xfour.businesscapitalloan.model.response.BillResponse;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.PagingResponse;
import com.xfour.businesscapitalloan.service.BillService;
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
@RequestMapping(path = "/api/bills")
@RequiredArgsConstructor
@Slf4j
public class BillController {
    private final BillService billService;

    @Operation(summary = "Get Bill By Id")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info("start of get bill by id");
        BillResponse billResponse = billService.getById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(billResponse)
                .build();
        log.info("end of get bill by id");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @Operation(summary = "Get All Bill")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("start of get all bill");
        SearchBillRequest request = SearchBillRequest.builder()
                .page(page)
                .size(size)
                .build();
        Page<BillResponse> billResponses = billService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .count(billResponses.getTotalElements())
                .totalPages(billResponses.getTotalPages())
                .page(page)
                .size(size)
                .build();
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(billResponses.getContent())
                .paging(pagingResponse)
                .build();
        log.info("end of get all bill");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @Operation(summary = "Update Bill For UMKM")
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DEBTOR')")
    public ResponseEntity<?> updateForUmkm(
            @RequestBody UpdateBillUmkmRequest request
    ) {
        log.info("start of update bill for umkm");
        BillResponse billResponse = billService.updateForUmkm(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(billResponse)
                .build();
        log.info("end of update bill for umkm");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @Operation(summary = "Update Bill For admin")
    @PutMapping(
            path = "/verify",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DEBTOR')")
    public ResponseEntity<?> updateForAdmin(
            @RequestBody UpdateBillAdminRequest request
    ) {
        log.info("start of update bill for admin");
        BillResponse billResponse = billService.updateForAdmin(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(billResponse)
                .build();
        log.info("end of update bill for admin");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }
}
