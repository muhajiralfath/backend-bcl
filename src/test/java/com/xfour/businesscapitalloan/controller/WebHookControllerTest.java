package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.UpdateBillUmkmRequest;
import com.xfour.businesscapitalloan.model.response.BillResponse;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.service.BillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebHookControllerTest {

    @InjectMocks
    private WebHookController webHookController;

    private BillService billService;

    @BeforeEach
    void setUp() {
        billService = mock(BillService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateBillStatusTransactionSettlement() {
        // Mocking
        Map<String, Object> request = new HashMap<>();
        request.put("transaction_status", "settlement");
        request.put("order_id", "your_order_id");
        BillResponse billResponse = new BillResponse();
        when(billService.updateForUmkm(any(UpdateBillUmkmRequest.class))).thenReturn(billResponse);

        // Test
        ResponseEntity<?> responseEntity = webHookController.updateBillStatus(request);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(billResponse, commonResponse.getData());
    }

    @Test
    void updateBillStatusTransactionNotSettlement() {
        // Mocking
        Map<String, Object> request = new HashMap<>();
        request.put("transaction_status", "other_status");
        request.put("order_id", "your_order_id");

        // Test
        ResponseEntity<?> responseEntity = webHookController.updateBillStatus(request);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(null, commonResponse.getData());
    }
}
