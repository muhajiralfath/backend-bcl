package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.File;
import com.xfour.businesscapitalloan.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FileServiceImplTest {

    private FileService fileService;

    @Mock
    private FileServiceImpl fileServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fileService = new FileServiceImpl();
    }

    @Test
    void create_ValidFile_Success() throws IOException {

    }

    @Test
    void get_ValidPath_ReturnsResource() {

    }

    @Test
    void delete_ValidPath_Success() throws IOException {

    }
}
