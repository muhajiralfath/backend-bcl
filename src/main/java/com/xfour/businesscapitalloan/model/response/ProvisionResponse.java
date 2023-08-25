package com.xfour.businesscapitalloan.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvisionResponse {

    private String id;
    private String submissionId;
    private String umkmName;
    private String debtorName;
    private String bankAccount;
    private Long amount;
    private LocalDateTime time;
}