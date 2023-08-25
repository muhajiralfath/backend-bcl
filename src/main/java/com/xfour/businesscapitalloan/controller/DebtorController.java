package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.SearchDebtorRequest;
import com.xfour.businesscapitalloan.model.request.UpdateDebtorRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.DebtorResponse;
import com.xfour.businesscapitalloan.model.response.PagingResponse;
import com.xfour.businesscapitalloan.service.DebtorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/debtors")
@Slf4j
public class DebtorController {
    private final DebtorService debtorService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Self Debtor Info")
    @GetMapping(path = "/me")
    public ResponseEntity<?> getByToken(Authentication authentication) {
        log.info("start getDebtorByToken");
        DebtorResponse debtorResponse = debtorService.getByAuthentication(authentication);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(debtorResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Debtor by Id")
    public ResponseEntity<?> getDebtorById(@PathVariable String id){
        DebtorResponse debtorResponse = debtorService.getById(id);
        CommonResponse<?> response = CommonResponse.builder()
                .data(debtorResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get All Debtors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "q", required = false) String keyword,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "page", required = false, defaultValue = "10") Integer size
    ){
        log.info("Start get all debtors");
        SearchDebtorRequest request = SearchDebtorRequest.builder()
                .keyword(keyword)
                .page(page)
                .size(size)
                .build();

        Page<DebtorResponse> debtorResponsePage = debtorService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .count(debtorResponsePage.getTotalElements())
                .totalPages(debtorResponsePage.getTotalPages())
                .page(page)
                .size(size)
                .build();
        CommonResponse<?> response = CommonResponse.builder()
                .data(debtorResponsePage.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update Debtor")
    @PreAuthorize("hasRole('DEBTOR')")
    public ResponseEntity<?> update(@RequestBody UpdateDebtorRequest request){
        log.info("Start update debtor");
        DebtorResponse updatedDebtor = debtorService.update(request);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .data(updatedDebtor)
                .build();

        log.info("Finish update Debtor");
        return ResponseEntity.ok(commonResponse);
    }
}
