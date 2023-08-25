package com.xfour.businesscapitalloan.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchSubmissionRequest {
    private Long minLoanAmount;
    private Long maxLoanAmount;
    private Integer page;
    private Integer size;
}
