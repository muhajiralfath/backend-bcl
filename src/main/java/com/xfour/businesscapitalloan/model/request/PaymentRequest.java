package com.xfour.businesscapitalloan.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    @NotBlank(message = "umkm id is required")
    private String umkmId;

    @NotNull(message = "amount is required")
    private Long amount;

    @NotBlank(message = "bill id is required")
    private String billId;

}
