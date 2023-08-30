package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.File;
import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.entity.UmkmDocument;
import com.xfour.businesscapitalloan.repository.UmkmDocumentRepository;
import com.xfour.businesscapitalloan.service.DebtorService;
import com.xfour.businesscapitalloan.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UmkmDocumentServiceImplTest {

    @Mock
    private UmkmDocumentRepository repository;

    @Mock
    private FileService fileService;

    @Mock
    private DebtorService debtorService;

    @InjectMocks
    private UmkmDocumentServiceImpl umkmDocumentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() throws IOException {
        // Mocking
        MultipartFile multipartFile = new MockMultipartFile("file", "filename.txt", "text/plain", "Hello, World!".getBytes());
        File file = File.builder()
                .name("filename.txt")
                .contentType("text/plain")
                .path("/path/to/filename.txt")
                .size(13L)
                .build();
        Umkm umkm = Umkm.builder().build();
        when(fileService.create(multipartFile)).thenReturn(file);
        when(repository.save(any(UmkmDocument.class))).thenReturn(UmkmDocument.builder().build());

        // Test
        UmkmDocument createdDocument = umkmDocumentService.create(umkm, multipartFile);

        // Verify
        assertNotNull(createdDocument);
        assertEquals(file.getName(), createdDocument.getName());
        assertEquals(file.getContentType(), createdDocument.getContentType());
        assertEquals(file.getPath(), createdDocument.getPath());
        assertEquals(file.getSize(), createdDocument.getSize());
    }

    @Test
    void getById() {
        // Mocking
        UmkmDocument umkmDocument = UmkmDocument.builder().build();
        when(repository.findById(anyString())).thenReturn(Optional.of(umkmDocument));

        // Test
        UmkmDocument retrievedDocument = umkmDocumentService.getById("123");

        // Verify
        assertNotNull(retrievedDocument);
    }

    @Test
    void getByUmkmId() {
        // Mocking
        UmkmDocument umkmDocument = UmkmDocument.builder().build();
        when(repository.findUmkmDocumentByUmkmId(anyString())).thenReturn(Optional.of(umkmDocument));

        // Test
        UmkmDocument retrievedDocument = umkmDocumentService.getByUmkmId("umkm123");

        // Verify
        assertNotNull(retrievedDocument);
    }


    @Test
    void deleteById_ThrowsExceptionWhenDocumentNotFound() {
        // Mocking
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // Test
        assertThrows(ResponseStatusException.class, () -> umkmDocumentService.deleteById("123"));
    }
}
