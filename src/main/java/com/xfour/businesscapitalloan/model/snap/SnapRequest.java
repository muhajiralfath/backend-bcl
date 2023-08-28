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
public class SnapRequest {

    @JsonProperty("transaction_details")
    private TransactionDetails transactionDetails;

    @JsonProperty("customer_details")
    private CustomerDetails customerDetails;
}
