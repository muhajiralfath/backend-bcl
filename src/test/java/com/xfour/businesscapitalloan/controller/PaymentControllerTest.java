package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.PaymentRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.PaymentResponse;
import com.xfour.businesscapitalloan.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = mock(PaymentService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        PaymentRequest paymentRequest = new PaymentRequest();
        PaymentResponse paymentResponse = new PaymentResponse();
        when(paymentService.create(eq(paymentRequest))).thenReturn(paymentResponse);

        ResponseEntity<?> responseEntity = paymentController.create(paymentRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(paymentResponse, commonResponse.getData());
    }

    @Test
    void getById() {
        String paymentId = "some-payment-id";
        PaymentResponse paymentResponse = new PaymentResponse();
        when(paymentService.getById(eq(paymentId))).thenReturn(paymentResponse);

        ResponseEntity<?> responseEntity = paymentController.getById(paymentId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(paymentResponse, commonResponse.getData());
    }

    @Test
    void getAll() {

    }
}
