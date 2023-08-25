package com.xfour.businesscapitalloan.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDebtorRequest {
    @NotBlank(message = "debtor id is required")
    private String debtorId;
    @NotBlank(message = "nik is required")
    private String nik;
    @NotBlank(message = "npwp is required")
    private String npwp;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "handphone is required")
    private String handphone;
    @NotBlank(message = "birthplace is required")
    private String birthPlace;
    @NotBlank(message = "birthdate is required")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date birthDate;
    @NotBlank(message = "gender is required")
    private String gender;
    @NotBlank(message = "status is required")
    private String status;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "job is required")
    private String job;

}
