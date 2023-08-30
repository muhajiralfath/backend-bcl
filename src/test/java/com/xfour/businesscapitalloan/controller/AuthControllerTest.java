package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.AuthRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.LoginResponse;
import com.xfour.businesscapitalloan.model.response.UserResponse;
import com.xfour.businesscapitalloan.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidRegistrationRequest_thenRegisterReturnsCreatedStatusAndUserResponse() {
        // Given
        AuthRequest validAuthRequest = new AuthRequest();
        UserResponse expectedUserResponse = new UserResponse();
        when(authService.registerDebtor(eq(validAuthRequest))).thenReturn(expectedUserResponse);

        // When
        ResponseEntity<?> responseEntity = authController.register(validAuthRequest);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertNotNull(commonResponse);
        assertEquals(expectedUserResponse, commonResponse.getData());
    }

    @Test
    void givenValidLoginRequest_thenLoginReturnsOkStatusAndLoginResponse() {
        // Given
        AuthRequest validAuthRequest = new AuthRequest();
        LoginResponse expectedLoginResponse = new LoginResponse();
        when(authService.login(eq(validAuthRequest))).thenReturn(expectedLoginResponse);

        // When
        ResponseEntity<?> responseEntity = authController.login(validAuthRequest);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertNotNull(commonResponse);
        assertEquals(expectedLoginResponse, commonResponse.getData());
    }
}
