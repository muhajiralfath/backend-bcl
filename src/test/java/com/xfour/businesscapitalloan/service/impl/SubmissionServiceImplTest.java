package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Submission;
import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.model.request.NewSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.SearchSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.UpdateSubmissionRequest;
import com.xfour.businesscapitalloan.model.response.SubmissionResponse;
import com.xfour.businesscapitalloan.repository.SubmissionRepository;
import com.xfour.businesscapitalloan.service.UmkmService;
import com.xfour.businesscapitalloan.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SubmissionServiceImplTest {

    @InjectMocks
    private SubmissionServiceImpl submissionService;

    @Mock
    private SubmissionRepository submissionRepository;

    @Mock
    private UmkmService umkmService;

    @Mock
    private ValidationUtil validationUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_NotFound() {
        // Given
        when(submissionRepository.findById("nonExistentId")).thenReturn(Optional.empty());

        // When, Then
        assertThrows(ResponseStatusException.class, () -> submissionService.findById("nonExistentId"));
    }




}
