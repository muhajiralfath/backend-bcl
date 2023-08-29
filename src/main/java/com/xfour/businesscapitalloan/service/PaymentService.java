package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Payment;
import com.xfour.businesscapitalloan.model.request.PaymentRequest;
import com.xfour.businesscapitalloan.model.request.SearchPaymentRequest;
import com.xfour.businesscapitalloan.model.response.PaymentResponse;
import org.springframework.data.domain.Page;

public interface PaymentService {
    PaymentResponse create(PaymentRequest request);
    PaymentResponse getById(String id);
    Payment findById(String id);
    Page<PaymentResponse> getAll(SearchPaymentRequest request);
}
