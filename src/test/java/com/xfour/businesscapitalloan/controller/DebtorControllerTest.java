package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.SearchDebtorRequest;
import com.xfour.businesscapitalloan.model.request.UpdateDebtorRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.DebtorResponse;
import com.xfour.businesscapitalloan.service.DebtorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DebtorControllerTest {

    @Mock
    private DebtorService debtorService;

    @InjectMocks
    private DebtorController debtorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByToken() {
        Authentication authentication = mock(Authentication.class);
        DebtorResponse debtorResponse = new DebtorResponse();
        when(debtorService.getByAuthentication(eq(authentication))).thenReturn(debtorResponse);

        ResponseEntity<?> responseEntity = debtorController.getByToken(authentication);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(debtorResponse, commonResponse.getData());
    }

    @Test
    void getDebtorById() {
        String debtorId = "123";
        DebtorResponse debtorResponse = new DebtorResponse();
        when(debtorService.getById(eq(debtorId))).thenReturn(debtorResponse);

        ResponseEntity<?> responseEntity = debtorController.getDebtorById(debtorId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(debtorResponse, commonResponse.getData());
    }

    @Test
    void getAll() {
        String keyword = "test";
        int page = 0;
        int size = 10;
        SearchDebtorRequest searchDebtorRequest = SearchDebtorRequest.builder()
                .keyword(keyword)
                .page(page)
                .size(size)
                .build();
        Page<DebtorResponse> debtorResponsePage = new PageImpl<>(new ArrayList<>());
        when(debtorService.getAll(eq(searchDebtorRequest))).thenReturn(debtorResponsePage);

        ResponseEntity<?> responseEntity = debtorController.getAll(keyword, page, size);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(debtorResponsePage.getContent(), commonResponse.getData());
    }

    @Test
    void update() {
        UpdateDebtorRequest request = new UpdateDebtorRequest();
        DebtorResponse updatedDebtor = new DebtorResponse();
        when(debtorService.update(eq(request))).thenReturn(updatedDebtor);

        ResponseEntity<?> responseEntity = debtorController.update(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(updatedDebtor, commonResponse.getData());
    }
}
