package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.model.response.SnapResponse;
import com.xfour.businesscapitalloan.model.snap.SnapRequest;
import org.springframework.http.ResponseEntity;

public interface SnapService {
    SnapResponse createTransaction(SnapRequest request);
}
