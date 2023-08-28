package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.SnapResponse;
import com.xfour.businesscapitalloan.model.snap.SnapRequest;
import com.xfour.businesscapitalloan.service.SnapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/snap")
@RequiredArgsConstructor
@Slf4j
public class SnapController {
    private final SnapService snapService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody SnapRequest request) {
        log.info("start snap");
        SnapResponse snapResponse = snapService.createTransaction(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(snapResponse)
                .build();
        log.info("end snap");
        return ResponseEntity.ok(commonResponse);
    }
}
