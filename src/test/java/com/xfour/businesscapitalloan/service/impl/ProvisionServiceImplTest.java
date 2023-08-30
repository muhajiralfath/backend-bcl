package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Provision;
import com.xfour.businesscapitalloan.repository.ProvisionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProvisionServiceImplTest {

    private ProvisionServiceImpl provisionService;

    @Mock
    private ProvisionRepository provisionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        provisionService = new ProvisionServiceImpl(provisionRepository);
    }

    @Test
    void findById_ExistingId_ShouldReturnProvision() {
        // Arrange
        String provisionId = "123";
        Provision provision = Provision.builder().id(provisionId).build();
        when(provisionRepository.findById(provisionId)).thenReturn(Optional.of(provision));

        // Act
        Provision result = provisionService.findById(provisionId);

        // Assert
        assertNotNull(result);
        assertEquals(provisionId, result.getId());
    }



}
