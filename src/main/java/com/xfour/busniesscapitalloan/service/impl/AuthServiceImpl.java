package com.xfour.busniesscapitalloan.service.impl;

import com.xfour.busniesscapitalloan.model.request.AuthRequest;
import com.xfour.busniesscapitalloan.model.response.LoginResponse;
import com.xfour.busniesscapitalloan.model.response.UserResponse;
import com.xfour.busniesscapitalloan.repository.UserCredentialRepository;
import com.xfour.busniesscapitalloan.security.BCryptUtils;
import com.xfour.busniesscapitalloan.security.JwtUtils;
import com.xfour.busniesscapitalloan.service.AdminService;
import com.xfour.busniesscapitalloan.service.AuthService;
import com.xfour.busniesscapitalloan.service.RoleService;
import com.xfour.busniesscapitalloan.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final BCryptUtils bCryptUtils;
    private final AdminService adminService;
//    private final CustomerService customerService;
    private final ValidationUtil validationUtil;
    @Override
    public UserResponse registerDebtor(AuthRequest request) {
        return null;
    }

    @Override
    public UserResponse registerAdmin(AuthRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        return null;
    }
}
