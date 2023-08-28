package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.service.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/webhook")
@RequiredArgsConstructor
@Slf4j
public class WebHookController {
    private final BillService billService;

    @PostMapping
    public ResponseEntity<?> updateBillStatus(@RequestBody Map<String, String> request) {
        log.info("start updateBillStatus");
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(request)
                .build();
        log.info(String.valueOf(commonResponse));
        log.info("end updateBillStatus");

        return ResponseEntity.ok(commonResponse);
    }
}
