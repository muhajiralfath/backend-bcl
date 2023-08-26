package com.xfour.businesscapitalloan.model.response;

import com.xfour.businesscapitalloan.entity.Bill;
import com.xfour.businesscapitalloan.entity.Umkm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private String paymentId;
    private Umkm umkm;
    private Bill bill;
    private LocalDate paymentDate;
    private Long amount;
    private Boolean isSuccess;
}
