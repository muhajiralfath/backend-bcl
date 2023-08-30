package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Admin;
import com.xfour.businesscapitalloan.repository.AdminRepository;
import com.xfour.businesscapitalloan.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    private AdminRepository repository;
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        adminService = new AdminServiceImpl(repository);
    }

    @Test
    void create() {
        // Arrange
        Admin admin = new Admin();
        when(repository.save(admin)).thenReturn(admin);

        // Act
        Admin result = adminService.create(admin);

        // Assert
        assertEquals(admin, result);
        verify(repository, times(1)).save(admin);
    }

    @Test
    void isAdminEmpty_whenNotEmpty() {
        // Arrange
        when(repository.count()).thenReturn(1L);

        // Act
        Boolean result = adminService.isAdminEmpty();

        // Assert
        assertFalse(result);
        verify(repository, times(1)).count();
    }

    @Test
    void isAdminEmpty_whenEmpty() {
        // Arrange
        when(repository.count()).thenReturn(0L);

        // Act
        Boolean result = adminService.isAdminEmpty();

        // Assert
        assertTrue(result);
        verify(repository, times(1)).count();
    }
}