package com.xfour.businesscapitalloan.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private Boolean isPaid;
    private Boolean isVerify;
}
