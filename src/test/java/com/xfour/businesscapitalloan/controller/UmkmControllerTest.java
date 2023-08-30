package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.entity.UmkmDocument;
import com.xfour.businesscapitalloan.model.request.NewUmkmRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import com.xfour.businesscapitalloan.service.UmkmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UmkmControllerTest {

    @InjectMocks
    private UmkmController umkmController;

    private UmkmService umkmService;

    @BeforeEach
    void setUp() {
        umkmService = mock(UmkmService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        // Mocking
        NewUmkmRequest newUmkmRequest = new NewUmkmRequest();
        UmkmResponse umkmResponse = new UmkmResponse();
        when(umkmService.create(eq(newUmkmRequest))).thenReturn(umkmResponse);

        // Test
        ResponseEntity<?> responseEntity = umkmController.create(newUmkmRequest);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), 201);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(umkmResponse, commonResponse.getData());
    }

    @Test
    void getById() {
        // Mocking
        String umkmId = "your_umkm_id";
        UmkmResponse umkmResponse = new UmkmResponse();
        when(umkmService.getById(eq(umkmId))).thenReturn(umkmResponse);

        // Test
        ResponseEntity<?> responseEntity = umkmController.getById(umkmId);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(umkmResponse, commonResponse.getData());
    }


    @Test
    void uploadDocument() {
        // Mocking
        Authentication authentication = mock(Authentication.class);
        MultipartFile multipartFile = mock(MultipartFile.class);
        UmkmDocument umkmDocument = new UmkmDocument();
        when(umkmService.uploadDocument(eq(authentication), eq(multipartFile))).thenReturn(umkmDocument);

        // Test
        ResponseEntity<?> responseEntity = umkmController.uploadDocument(authentication, multipartFile);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), 201);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(umkmDocument, commonResponse.getData());
    }

    @Test
    void downloadDocument() {
        // Mocking
        Authentication authentication = mock(Authentication.class);
        Resource resource = mock(Resource.class);
        when(umkmService.downloadDocument(eq(authentication))).thenReturn(resource);

        // Test
        ResponseEntity<?> responseEntity = umkmController.uploadDocument(authentication);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(resource, responseEntity.getBody());
    }
}
