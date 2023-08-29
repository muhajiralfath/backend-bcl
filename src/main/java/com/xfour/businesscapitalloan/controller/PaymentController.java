package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.PaymentRequest;
import com.xfour.businesscapitalloan.model.request.SearchPaymentRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.PagingResponse;
import com.xfour.businesscapitalloan.model.response.PaymentResponse;
import com.xfour.businesscapitalloan.service.PaymentService;
import com.xfour.businesscapitalloan.service.SnapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasRole('DEBTOR')")
    public ResponseEntity<?> create(@RequestBody PaymentRequest request) {
        PaymentResponse paymentResponse = paymentService.create(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(paymentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        PaymentResponse paymentResponse = paymentService.getById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(paymentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "100") Integer size
    ) {
        SearchPaymentRequest searchPaymentRequest = SearchPaymentRequest.builder()
                .page(page)
                .size(size)
                .build();
        Page<PaymentResponse> paymentResponses = paymentService.getAll(searchPaymentRequest);
        PagingResponse pagingResponse = PagingResponse.builder()
                .count(paymentResponses.getTotalElements())
                .totalPages(paymentResponses.getTotalPages())
                .page(page)
                .size(size)
                .build();
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(paymentResponses.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(commonResponse);
    }
}
