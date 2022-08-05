package com.iknow.stocktrackingbe.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionProductResponse {
    private int quantity;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("usage_descriptions")
    private String usageDescriptions;
    @JsonProperty("start_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate startDate;
    @JsonProperty("end_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate endDate;
}
