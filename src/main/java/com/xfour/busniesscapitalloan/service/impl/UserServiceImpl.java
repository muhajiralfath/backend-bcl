package com.xfour.busniesscapitalloan.service.impl;

import com.xfour.busniesscapitalloan.entity.UserCredential;
import com.xfour.busniesscapitalloan.entity.UserDetailsImpl;
import com.xfour.busniesscapitalloan.model.response.UserResponse;
import com.xfour.busniesscapitalloan.repository.UserCredentialRepository;
import com.xfour.busniesscapitalloan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository userCredentialRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("start loadUserByUserId");
        UserCredential userCredential = userCredentialRepository.findById(userId).orElseThrow(() ->
                new UsernameNotFoundException("user is not found"));
        log.info("end loadUserByUserId");

        return UserDetailsImpl.builder()
                .userId(userCredential.getId())
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .authorities(userCredential.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getByToken(Authentication authentication) {
        log.info("start getByToken");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserCredential userCredential = userCredentialRepository.findById(userDetails.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "credential is invalid"));
        log.info("end getByToken");
        return toUserResponse(userCredential);
    }

    @Override
    public UserCredential getByAuthentication(Authentication authentication) {
        log.info("start getByAuthentication");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserCredential userCredential = userCredentialRepository.findById(userDetails.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "credential is invalid"));
        log.info("end getByAuthentication");
        return userCredential;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("start loadUserByUsername");
        UserCredential userCredential = userCredentialRepository.findFirstByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("user is not found"));
        log.info("end loadUserByUsername");
        return UserDetailsImpl.builder()
                .userId(userCredential.getId())
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .authorities(userCredential.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                        .collect(Collectors.toList()))
                .build();
    }

    private UserResponse toUserResponse(UserCredential userCredential) {
        return UserResponse.builder()
                .userId(userCredential.getId())
                .roles(userCredential.getRoles().stream()
                        .map(role -> role.getRole().name())
                        .collect(Collectors.toList()))
                .build();
    }
}
