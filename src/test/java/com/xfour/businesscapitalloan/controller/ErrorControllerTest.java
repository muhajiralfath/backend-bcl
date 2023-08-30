package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.exception.BadRequestException;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ErrorControllerTest {

    @InjectMocks
    private ErrorController errorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void constraintViolationExceptionHandler() {
    }

    @Test
    void responseStatusExceptionHandler() {
    }

    @Test
    void badRequestExceptionHandler() {

        BadRequestException exception = mock(BadRequestException.class);
        Map<String, String> errors = new HashMap<>();
        when(exception.getErrors()).thenReturn(errors);

        ResponseEntity<?> responseEntity = errorController.badRequestExceptionHandler(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(errors, commonResponse.getErrors());
    }
}
