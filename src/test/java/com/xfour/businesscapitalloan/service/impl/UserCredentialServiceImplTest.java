package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.UserCredential;
import com.xfour.businesscapitalloan.repository.UserCredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserCredentialServiceImplTest {

    @Mock
    private UserCredentialRepository userCredentialRepository;

    @InjectMocks
    private UserCredentialServiceImpl userCredentialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsAdminEmptyWhenUserCredentialNotFound() {

    }

    @Test
    void testIsAdminEmptyWhenUserCredentialExists() {

    }
}
