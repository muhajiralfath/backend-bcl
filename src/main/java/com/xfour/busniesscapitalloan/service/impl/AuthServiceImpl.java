package com.xfour.busniesscapitalloan.service.impl;

import com.xfour.busniesscapitalloan.model.request.AuthRequest;
import com.xfour.busniesscapitalloan.model.response.LoginResponse;
import com.xfour.busniesscapitalloan.model.response.UserResponse;
import com.xfour.busniesscapitalloan.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
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
