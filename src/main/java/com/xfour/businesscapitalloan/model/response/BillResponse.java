package com.xfour.businesscapitalloan.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponse {

    private String id;
    private String provisionId;
    private String umkmName;
    private String debtorName;
    private Long debt;
    private Integer interest;
    private LocalDate dueDate;
    private Boolean isPaid;
    private Boolean isVerify;
}
