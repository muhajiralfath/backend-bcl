package com.xfour.busniesscapitalloan.service;

import com.xfour.busniesscapitalloan.entity.UserCredential;
import com.xfour.busniesscapitalloan.model.response.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException;
    UserCredential getByAuthentication(Authentication authentication);
    UserResponse getByToken(Authentication authentication);
}
