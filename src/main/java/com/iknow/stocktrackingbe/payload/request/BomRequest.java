package com.iknow.stocktrackingbe.payload.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomRequest {
    @JsonProperty("bom_code")
    private String bomCode;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    private String productId;
    @JsonProperty("bom_detail")
    private List<BomDetailRequest> bomDetailRequest;
    @JsonProperty("main_amount")
    private Long mainAmount;
}
