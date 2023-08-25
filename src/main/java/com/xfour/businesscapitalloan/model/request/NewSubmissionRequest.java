package com.xfour.businesscapitalloan.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewSubmissionRequest {
    @NotBlank(message = "umkmId is required")
    private String umkmId;

    @NotNull(message = "loanAmount is required")
    private Long loanAmount;

    @NotNull(message = "tenor is required")
    private Integer tenor;
}
