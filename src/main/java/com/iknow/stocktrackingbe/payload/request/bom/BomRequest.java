package com.iknow.stocktrackingbe.payload.request.bom;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("quantity")
    private BigDecimal quantity;
    private BigDecimal efficiency;
    private String description;
    @JsonProperty("ware_house_id")
    private String wareHouseId;
}
