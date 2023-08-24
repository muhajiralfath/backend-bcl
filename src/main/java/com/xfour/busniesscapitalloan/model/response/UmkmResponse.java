package com.xfour.busniesscapitalloan.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UmkmResponse {
    private String umkmId;
    private String noSiup;
    private String umkmName;
    private String address;
    private String capital;
    private String umkmType;
    private String bankAccount;
}
