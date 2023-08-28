package com.xfour.businesscapitalloan.model.snap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {

    @JsonProperty("order_id")
    private String billId;

    @JsonProperty("gross_amount")
    private Long Amount;
}
