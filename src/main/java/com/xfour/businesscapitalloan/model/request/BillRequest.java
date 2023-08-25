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
public class BillRequest {

    @NotBlank(message = "id is required")
    private String id;

    @NotNull(message = "isPaid is required")
    private Boolean isPaid;
}
