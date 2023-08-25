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
public class SubmissionResponse {
    private String id;
    private String umkmId;
    private LocalDateTime date;
    private Long loanAmount;
    private Integer tenor;
    private Boolean isApprove;
}
