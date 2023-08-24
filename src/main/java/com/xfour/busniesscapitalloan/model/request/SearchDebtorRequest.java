package com.xfour.busniesscapitalloan.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDebtorRequest {
    private String keyword;
    private Integer page;
    private Integer size;
}
