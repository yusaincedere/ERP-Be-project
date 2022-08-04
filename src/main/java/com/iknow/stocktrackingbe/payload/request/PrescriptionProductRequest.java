package com.iknow.stocktrackingbe.payload.request;

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
public class PrescriptionProductRequest {

    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonProperty("product_id")
    private String productId;
    private int quantity;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("usage_descriptions")
    private String usageDescriptions;

}
