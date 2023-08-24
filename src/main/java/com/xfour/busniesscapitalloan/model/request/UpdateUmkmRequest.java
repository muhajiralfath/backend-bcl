package com.xfour.busniesscapitalloan.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUmkmRequest {

    @NotBlank(message = "umkm id is required")
    private String umkmId;
    @NotBlank(message = "umkm name is Required")
    private String umkmName;
    @NotBlank(message = "np siup is Required")
    private String noSiup;
    @NotBlank(message = "address is Required")
    private String address;
    @NotBlank(message = "capital is Required")
    private Long capital;
    @NotBlank(message = "umkm type is Required")
    private String umkmType;
    @NotBlank(message = "bank account is Required")
    private String bankAccount;
}
