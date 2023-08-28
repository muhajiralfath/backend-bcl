package com.xfour.businesscapitalloan.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfour.businesscapitalloan.model.response.SnapResponse;
import com.xfour.businesscapitalloan.model.snap.SnapRequest;
import com.xfour.businesscapitalloan.service.SnapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class SnapServiceImpl implements SnapService {

    @Value("${midtrans.server-key}")
    private String serverKey;
    @Value("${midtrans.client-key}")
    private String clientKey;
    @Value("${midtrans.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public SnapResponse createTransaction(SnapRequest requestBody) {
        log.info("start createTransaction");
//        String requestBody = "";
//        try {
//            requestBody = objectMapper.writeValueAsString(request);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "application/json");
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((serverKey + ":").getBytes()));

        HttpEntity<SnapRequest> httpEntity = new HttpEntity<>(requestBody, requestHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        JSONObject body = new JSONObject(response.getBody());
        log.info("end createTransaction");

        return SnapResponse.builder()
                .token(body.getString("token"))
                .redirectUrl(body.getString("redirect_url"))
                .build();
    }
}
