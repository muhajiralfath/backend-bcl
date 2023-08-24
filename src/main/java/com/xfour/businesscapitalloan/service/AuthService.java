package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.model.request.AuthRequest;
import com.xfour.businesscapitalloan.model.response.LoginResponse;
import com.xfour.businesscapitalloan.model.response.UserResponse;

public interface AuthService {
    UserResponse registerDebtor(AuthRequest request);
    UserResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
