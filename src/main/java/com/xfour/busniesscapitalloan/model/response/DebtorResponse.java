package com.xfour.busniesscapitalloan.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebtorResponse {
    private String debtorId;
    private String nik;
    private String npwp;
    private String name;
    private String handphone;
    private String birthPlace;
    private LocalDateTime birthDate;
    private String gender;
    private String status;
    private String address;
    private String job;
    private String email;
    private String umkmId;
    private String umkmName;
}
