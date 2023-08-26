package com.xfour.businesscapitalloan.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
}
