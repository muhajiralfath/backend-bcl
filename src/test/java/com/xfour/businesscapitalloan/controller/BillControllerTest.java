package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.SearchBillRequest;
import com.xfour.businesscapitalloan.model.request.UpdateBillAdminRequest;
import com.xfour.businesscapitalloan.model.request.UpdateBillUmkmRequest;
import com.xfour.businesscapitalloan.model.response.BillResponse;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.PagingResponse;
import com.xfour.businesscapitalloan.service.BillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BillControllerTest {

    @Mock
    private BillService billService;

    @InjectMocks
    private BillController billController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        // Mocking
        String billId = "123";
        BillResponse billResponse = new BillResponse();
        when(billService.getById(eq(billId))).thenReturn(billResponse);

        // Test
        ResponseEntity<?> responseEntity = billController.getById(billId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(billResponse, commonResponse.getData());
    }

    @Test
    void getAll() {
        // Mocking
        int page = 0;
        int size = 100;
        SearchBillRequest searchBillRequest = SearchBillRequest.builder()
                .page(page)
                .size(size)
                .build();
        Page<BillResponse> billResponses = new PageImpl<>(new ArrayList<>());
        when(billService.getAll(eq(searchBillRequest))).thenReturn(billResponses);

        // Test
        ResponseEntity<?> responseEntity = billController.getAll(page, size);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(billResponses.getContent(), commonResponse.getData());
    }

    @Test
    void updateForUmkm() {
        // Mocking
        UpdateBillUmkmRequest request = new UpdateBillUmkmRequest();
        BillResponse billResponse = new BillResponse();
        when(billService.updateForUmkm(eq(request))).thenReturn(billResponse);

        // Test
        ResponseEntity<?> responseEntity = billController.updateForUmkm(request);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(billResponse, commonResponse.getData());
    }

    @Test
    void updateForAdmin() {
        // Mocking
        UpdateBillAdminRequest request = new UpdateBillAdminRequest();
        BillResponse billResponse = new BillResponse();
        when(billService.updateForAdmin(eq(request))).thenReturn(billResponse);

        // Test
        ResponseEntity<?> responseEntity = billController.updateForAdmin(request);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(billResponse, commonResponse.getData());
    }

    @Test
    void getAllBillByDebtorId() {
        // Mocking
        String debtorId = "456";
        List<BillResponse> billResponseList = new ArrayList<>();
        when(billService.getAllBillByDebtorId(eq(debtorId))).thenReturn(billResponseList);

        // Test
        ResponseEntity<?> responseEntity = billController.getAllBillByDebtorId(debtorId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(billResponseList, commonResponse.getData());
    }
}
