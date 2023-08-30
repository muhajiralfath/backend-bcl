package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.SnapResponse;
import com.xfour.businesscapitalloan.model.snap.SnapRequest;
import com.xfour.businesscapitalloan.service.SnapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SnapControllerTest {

    @InjectMocks
    private SnapController snapController;

    private SnapService snapService;

    @BeforeEach
    void setUp() {
        snapService = mock(SnapService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction() {
        // Mocking
        SnapRequest snapRequest = new SnapRequest(); // Initialize your snap request
        SnapResponse snapResponse = new SnapResponse(); // Initialize your snap response
        when(snapService.createTransaction(eq(snapRequest))).thenReturn(snapResponse);

        // Test
        ResponseEntity<?> responseEntity = snapController.createTransaction(snapRequest);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(snapResponse, commonResponse.getData());
    }
}
