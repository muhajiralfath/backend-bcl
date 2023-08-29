package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.UpdateBillUmkmRequest;
import com.xfour.businesscapitalloan.model.response.BillResponse;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.service.BillService;
import com.xfour.businesscapitalloan.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/webhook")
@RequiredArgsConstructor
@Slf4j
public class WebHookController {
    private final BillService billService;

    @PostMapping
    public ResponseEntity<?> updateBillStatus(@RequestBody Map<String, Object> request) {
        log.info("start updateBillStatus");
        String transactionStatus = (String) request.get("transaction_status");
        String  billId = (String) request.get("order_id");
        BillResponse billResponse = null;

        if (Objects.equals(transactionStatus, "settlement")) {
            UpdateBillUmkmRequest updateBillUmkmRequest = UpdateBillUmkmRequest.builder()
                    .id(billId)
                    .isPaid(true)
                    .build();
            billResponse = billService.updateForUmkm(updateBillUmkmRequest);
        }
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(billResponse)
                .build();
        log.info("end updateBillStatus");

        return ResponseEntity.ok(commonResponse);
    }
}
