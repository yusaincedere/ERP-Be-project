package com.iknow.stocktrackingbe.payload.response.bom;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomDetailResponse {
    @JsonProperty("bom_detail_id")
    private String bomDetailId;
    @JsonProperty("child_product_name")
    private String childProductName;
    @JsonProperty("child_product_id")
    private String childProductId;
    private BigDecimal quantity;

    private BigDecimal efficiency;

    private String description;

}
