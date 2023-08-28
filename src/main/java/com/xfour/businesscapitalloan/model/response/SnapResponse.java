package com.xfour.businesscapitalloan.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SnapResponse {

    @JsonProperty("token")
    private String token;
    @JsonProperty("redirect_url")
    private String redirectUrl;
}
