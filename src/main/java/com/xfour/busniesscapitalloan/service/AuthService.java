package com.xfour.busniesscapitalloan.service;

import com.xfour.busniesscapitalloan.model.request.AuthRequest;
import com.xfour.busniesscapitalloan.model.response.LoginResponse;
import com.xfour.busniesscapitalloan.model.response.UserResponse;

public interface AuthService {
    UserResponse registerDebtor(AuthRequest request);
    UserResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
