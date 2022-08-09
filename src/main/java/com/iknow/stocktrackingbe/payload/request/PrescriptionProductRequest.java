package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionProductRequest {

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("end_date")
    private LocalDate endDate;
    @NotEmpty
    @JsonProperty("product_id")
    private String productId;
    private int quantity;
    @JsonProperty("start_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonProperty("usage_descriptions")
    private String usageDescriptions;

}
