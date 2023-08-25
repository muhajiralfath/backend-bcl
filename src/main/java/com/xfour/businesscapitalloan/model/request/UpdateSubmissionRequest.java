package com.xfour.businesscapitalloan.model.request;

import javax.validation.constraints.NotBlank;

public class UpdateSubmissionRequest {

    @NotBlank(message = "id is required")
    private String id;

    @NotBlank(message = "approval is required")
    private Boolean isApprove;
}
