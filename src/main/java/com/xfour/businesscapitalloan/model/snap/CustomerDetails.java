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
public class CustomerDetails {

    @JsonProperty("first_name")
    private String debtorName;

    @JsonProperty("last_name")
    private String umkmName;
}
