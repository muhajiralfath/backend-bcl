package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.ProvisionResponse;
import com.xfour.businesscapitalloan.service.ProvisionService;
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

class ProvisionControllerTest {

    @InjectMocks
    private ProvisionController provisionController;

    private ProvisionService provisionService;

    @BeforeEach
    void setUp() {
        provisionService = mock(ProvisionService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        // Mocking
        String provisionId = "some-provision-id";
        ProvisionResponse provisionResponse = new ProvisionResponse(); // Initialize your provision response
        when(provisionService.getById(eq(provisionId))).thenReturn(provisionResponse);

        // Test
        ResponseEntity<?> responseEntity = provisionController.getById(provisionId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(provisionResponse, commonResponse.getData());
    }

    @Test
    void getAll() {

    }
}
