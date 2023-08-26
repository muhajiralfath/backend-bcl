package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Bill;
import com.xfour.businesscapitalloan.entity.Payment;
import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.model.request.PaymentRequest;
import com.xfour.businesscapitalloan.model.request.SearchPaymentRequest;
import com.xfour.businesscapitalloan.model.response.PaymentResponse;
import com.xfour.businesscapitalloan.repository.PaymentRepository;
import com.xfour.businesscapitalloan.service.BillService;
import com.xfour.businesscapitalloan.service.PaymentService;
import com.xfour.businesscapitalloan.service.UmkmService;
import com.xfour.businesscapitalloan.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final BillService billService;
    private final ValidationUtil validationUtil;
    private final UmkmService umkmService;

    @Override
    public PaymentResponse create(PaymentRequest request) {
        log.info("start payment transaction");
        validationUtil.validate(request);

        Bill bill = billService.findById(request.getBillId());
        Umkm umkm = umkmService.findById(request.getUmkmId());
        bill.setIsVerify(true);
        billService.create(bill);

        Payment newPayment =  Payment.builder()
                .paymentDate(LocalDate.now())
                .umkm(umkm)
                .bill(bill)
                .amount(request.getAmount())
                .build();

        paymentRepository.save(newPayment);
        log.info("finish payment transaction");
        return toPaymentResponse(newPayment);
    }

    @Override
    public PaymentResponse getById(String id) {
        log.info("start get payment by id");
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not Found"));
        log.info("finish get payment");
        return toPaymentResponse(payment);
    }

    @Override
    public Page<PaymentResponse> getAll(SearchPaymentRequest request) {
        log.info("start get all payment");
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Payment> payments = paymentRepository.findAll(pageable);

        List<PaymentResponse> paymentResponses = new ArrayList<>();
        for (Payment payment: payments.getContent()){
            paymentResponses.add(toPaymentResponse(payment));
        }

        return new PageImpl<>(paymentResponses, pageable, payments.getTotalElements());

    }

    private PaymentResponse toPaymentResponse(Payment payment){
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .paymentDate(payment.getPaymentDate())
                .umkm(payment.getUmkm())
                .bill(payment.getBill())
                .amount(payment.getAmount())
                .isSuccess(true)
                .build();
    }


}
