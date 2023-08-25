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
public class UpdateSubmissionRequest {

    @NotBlank(message = "id is required")
    private String id;

    @NotNull(message = "approval is required")
    private Boolean isApprove;
}
