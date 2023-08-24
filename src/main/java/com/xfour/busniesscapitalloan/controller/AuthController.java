package com.xfour.busniesscapitalloan.controller;

import com.xfour.busniesscapitalloan.model.request.AuthRequest;
import com.xfour.busniesscapitalloan.model.response.CommonResponse;
import com.xfour.busniesscapitalloan.model.response.LoginResponse;
import com.xfour.busniesscapitalloan.model.response.UserResponse;
import com.xfour.busniesscapitalloan.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Auth", description = "Auth APIs")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Register New Debtor")
    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        log.info("start registerDebtor");
        UserResponse userResponse = authService.registerDebtor(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(userResponse)
                .build();
        log.info("end registerDebtor");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @Operation(summary = "Login")
    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        log.info("start login");

        LoginResponse login = authService.login(request);
        CommonResponse<?> response = CommonResponse.builder()
                .data(login)
                .build();
        log.info("end login");
        return ResponseEntity.ok(response);
    }
}
