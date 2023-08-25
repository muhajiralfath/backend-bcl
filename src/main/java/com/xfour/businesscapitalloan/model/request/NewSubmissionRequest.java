package com.xfour.businesscapitalloan.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewSubmissionRequest {
    @NotBlank(message = "umkmId is required")
    private String umkmId;

    @NotBlank(message = "loanAmount is required")
    private Long loanAmount;

    @NotBlank(message = "tenor is required")
    private Integer tenor;
}
