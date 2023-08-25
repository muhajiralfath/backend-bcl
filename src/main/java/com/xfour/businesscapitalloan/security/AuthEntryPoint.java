package com.xfour.businesscapitalloan.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .errors(authException.getMessage())
                .build();

        String commonResponseString = objectMapper.writeValueAsString(commonResponse);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(commonResponseString);
    }
}
