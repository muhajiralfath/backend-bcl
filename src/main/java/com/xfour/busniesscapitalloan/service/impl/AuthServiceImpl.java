package com.xfour.busniesscapitalloan.service.impl;

import com.xfour.busniesscapitalloan.entity.*;
import com.xfour.busniesscapitalloan.model.request.AuthRequest;
import com.xfour.busniesscapitalloan.model.response.LoginResponse;
import com.xfour.busniesscapitalloan.model.response.UserResponse;
import com.xfour.busniesscapitalloan.repository.UserCredentialRepository;
import com.xfour.busniesscapitalloan.security.BCryptUtils;
import com.xfour.busniesscapitalloan.security.JwtUtils;
import com.xfour.busniesscapitalloan.service.AdminService;
import com.xfour.busniesscapitalloan.service.AuthService;
import com.xfour.busniesscapitalloan.service.DebtorService;
import com.xfour.busniesscapitalloan.service.RoleService;
import com.xfour.busniesscapitalloan.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

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
    private final ValidationUtil validationUtil;
    private final DebtorService debtorService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserResponse registerDebtor(AuthRequest request) {
        log.info("start register debtor");
        validationUtil.validate(request);

        try {
            Role role = roleService.getOrSave("1");
            UserCredential userCredential = UserCredential.builder()
                    .email(request.getEmail().toLowerCase())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .roles(Set.of(role))
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            debtorService.create(Debtor.builder().userCredential(userCredential).build());

            log.info("end register debtor");
            return UserResponse.builder()
                    .userId(userCredential.getId())
                    .roles(userCredential.getRoles().stream().map(r -> r.getRole().name()).collect(Collectors.toList()))
                    .build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email sudah terdaftar");
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserResponse registerAdmin(AuthRequest request) {
        log.info("start register admin");
        validationUtil.validate(request);

        try {
            Role role = roleService.getOrSave("2");
            UserCredential userCredential = UserCredential.builder()
                    .email(request.getEmail().toLowerCase())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .roles(Set.of(role))
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            adminService.create(Admin.builder()
                    .userCredential(userCredential)
                    .build());

            log.info("end registerAdmin");
            return UserResponse.builder()
                    .userId(userCredential.getId())
                    .roles(userCredential.getRoles().stream().map(r -> r.getRole().name()).collect(Collectors.toList()))
                    .build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email sudah terdaftar");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public LoginResponse login(AuthRequest request) {
        log.info("start login");
        validationUtil.validate(request);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail().toLowerCase(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);

        log.info("end login");
        return LoginResponse.builder()
                .token(token)
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();
    }
}
