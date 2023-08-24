package com.xfour.busniesscapitalloan.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionResponse {
    private String id;
    private String umkmId;
    private Long loanAmount;
    private Integer tenor;
    private Boolean isApprove;
}
