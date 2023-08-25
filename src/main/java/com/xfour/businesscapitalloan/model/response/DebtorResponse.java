package com.xfour.businesscapitalloan.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date birthDate;
    private String gender;
    private String status;
    private String address;
    private String job;
    private String email;
}
