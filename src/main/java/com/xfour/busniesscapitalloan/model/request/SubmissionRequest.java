package com.xfour.busniesscapitalloan.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionRequest {

    private String id;
    private String umkmId;
    private Long loanAmount;
    private Integer tenor;
}
