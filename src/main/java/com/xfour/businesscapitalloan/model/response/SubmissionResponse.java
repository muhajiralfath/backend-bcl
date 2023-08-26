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
public class SubmissionResponse {
    private String id;
    private String debtorId;
    private String umkmId;
    private String umkmName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private Long loanAmount;
    private Integer tenor;
    private Long debt;
    private Long monthlyDebt;
    private Boolean isApprove;
}
