package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import com.xfour.businesscapitalloan.repository.UmkmRepository;
import com.xfour.businesscapitalloan.service.DebtorService;
import com.xfour.businesscapitalloan.service.UmkmDocumentService;
import com.xfour.businesscapitalloan.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UmkmServiceImplTest {

    private UmkmServiceImpl umkmService;

    @Mock
    private UmkmRepository umkmRepository;

    @Mock
    private DebtorService debtorService;

    @Mock
    private UmkmDocumentService documentService;

    @Mock
    private ValidationUtil validationUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        umkmService = new UmkmServiceImpl(umkmRepository, debtorService, documentService, validationUtil);
    }

    @Test
    void getById_ValidId_ReturnsUmkmResponse() {
        // Arrange
        String umkmId = "validUmkmId";
        Umkm umkm = new Umkm();
        when(umkmRepository.findById(any())).thenReturn(Optional.of(umkm));

        UmkmResponse umkmResponse = umkmService.getById(umkmId);

        assertNotNull(umkmResponse);
    }

}
