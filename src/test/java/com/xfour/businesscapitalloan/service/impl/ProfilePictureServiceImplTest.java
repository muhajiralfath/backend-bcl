package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.File;
import com.xfour.businesscapitalloan.entity.ProfilePicture;
import com.xfour.businesscapitalloan.entity.UserCredential;
import com.xfour.businesscapitalloan.repository.ProfilePictureRepository;
import com.xfour.businesscapitalloan.security.UserSecurity;
import com.xfour.businesscapitalloan.service.FileService;
import com.xfour.businesscapitalloan.service.impl.ProfilePictureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProfilePictureServiceImplTest {

    private ProfilePictureServiceImpl profilePictureService;

    @Mock
    private ProfilePictureRepository profilePictureRepository;

    @Mock
    private FileService fileService;

    @Mock
    private UserSecurity userSecurity;

    @BeforeEach
    void setUp() {
        profilePictureService = new ProfilePictureServiceImpl(profilePictureRepository, fileService, userSecurity);
    }

    @Test
    void create_Success() throws IOException {
        UserCredential userCredential = new UserCredential();
        MultipartFile multipartFile = new MockMultipartFile("test.jpg", new byte[0]);
        ProfilePicture profilePicture = ProfilePicture.builder()
                .user(userCredential)
                .build();

        when(profilePictureRepository.saveAndFlush(any())).thenReturn(profilePicture);
        when(fileService.create(multipartFile)).thenReturn(File.builder().name("test.jpg").size(0L).contentType("image/jpeg").path("/test.jpg").build());

        ProfilePicture result = profilePictureService.create(userCredential, multipartFile);

        assertEquals("test.jpg", result.getName());
        assertEquals(0L, result.getSize());
        assertEquals("image/jpeg", result.getContentType());
        assertEquals("/test.jpg", result.getPath());

        verify(profilePictureRepository, times(1)).saveAndFlush(any());
        verify(fileService, times(1)).create(multipartFile);
    }

    @Test
    void create_Conflict() throws IOException {
        UserCredential userCredential = new UserCredential();
        MultipartFile multipartFile = new MockMultipartFile("test.jpg", new byte[0]);

        when(profilePictureRepository.saveAndFlush(any())).thenThrow(new RuntimeException()); // Simulate conflict

        assertThrows(ResponseStatusException.class, () -> profilePictureService.create(userCredential, multipartFile));
        verify(fileService, never()).create(any());
    }

    @Test
    void download_Success() throws IOException {
        ProfilePicture profilePicture = ProfilePicture.builder()
                .path("/test.jpg")
                .build();
        Resource resource = new org.springframework.core.io.ByteArrayResource(new byte[0]);

        when(profilePictureRepository.findById(any())).thenReturn(Optional.of(profilePicture));
        when(fileService.get("/test.jpg")).thenReturn(resource);

        Resource result = profilePictureService.download("123");

        assertSame(resource, result);
    }

    @Test
    void download_NotFound() {
        when(profilePictureRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> profilePictureService.download("123"));
        verify(fileService, never()).get(any());
    }

    @Test
    void deleteById_Success() throws IOException {
        ProfilePicture profilePicture = ProfilePicture.builder()
                .path("/test.jpg")
                .user(new UserCredential())
                .build();

        when(profilePictureRepository.findById(any())).thenReturn(Optional.of(profilePicture));

        profilePictureService.deleteById("123");

        verify(profilePictureRepository, times(1)).delete(profilePicture);
        verify(fileService, times(1)).delete("/test.jpg");
    }

    @Test
    void deleteById_NotFound() {
        when(profilePictureRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> profilePictureService.deleteById("123"));
        verify(fileService, never()).delete(any());
    }
}
