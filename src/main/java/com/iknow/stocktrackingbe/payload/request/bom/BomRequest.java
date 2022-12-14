package com.iknow.stocktrackingbe.payload.request.bom;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomRequest {
    @JsonProperty("bom_code")
    private String bomCode;
    @JsonProperty("bom_name")
    private String bomName;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("quantity")
    private BigDecimal quantity;
    private BigDecimal efficiency;
    private String description;
    @JsonProperty("ware_house_id")
    private Long wareHouseId;
}
