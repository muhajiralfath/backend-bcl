package com.xfour.businesscapitalloan.service.impl;


import com.xfour.businesscapitalloan.repository.UserCredentialRepository;
import com.xfour.businesscapitalloan.service.AdminService;
import com.xfour.businesscapitalloan.service.DebtorService;
import com.xfour.businesscapitalloan.service.RoleService;
import com.xfour.businesscapitalloan.security.BCryptUtils;
import com.xfour.businesscapitalloan.security.JwtUtils;
import com.xfour.businesscapitalloan.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;


class AuthServiceImplTest {

    @Mock
    private UserCredentialRepository userCredentialRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private BCryptUtils bCryptUtils;

    @Mock
    private AdminService adminService;

    @Mock
    private ValidationUtil validationUtil;

    @Mock
    private DebtorService debtorService;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerDebtor() {

    }

    @Test
    void login() {
    }
}
