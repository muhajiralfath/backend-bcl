package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.FileResponse;
import com.xfour.businesscapitalloan.model.response.UserResponse;
import com.xfour.businesscapitalloan.service.UserService;
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

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByToken() {
        // Mocking
        Authentication authentication = mock(Authentication.class);
        UserResponse userResponse = new UserResponse();
        when(userService.getByToken(eq(authentication))).thenReturn(userResponse);

        // Test
        ResponseEntity<?> responseEntity = userController.getByToken(authentication);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(userResponse, commonResponse.getData());
    }

    @Test
    void updateProfilePicture() {
        // Mocking
        MultipartFile multipartFile = mock(MultipartFile.class);
        FileResponse fileResponse = new FileResponse();
        when(userService.updateProfilePicture(eq(multipartFile))).thenReturn(fileResponse);

        // Test
        ResponseEntity<?> responseEntity = userController.updateProfilePicture(multipartFile);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(fileResponse, commonResponse.getData());
    }

    @Test
    void deleteProfilePicture() {
        // Mocking
        String imageId = "your_image_id";

        // Test
        ResponseEntity<?> responseEntity = userController.deleteProfilePicture(imageId);

        // Verify
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals("DELETE SUCCESS", commonResponse.getData());
    }
}
