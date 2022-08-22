package com.iknow.stocktrackingbe.payload.response.bom;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomDetailResponse {
    @JsonProperty("bom_detail_id")
    private Long bomDetailId;
    @JsonProperty("child_product_name")
    private String childProductName;
    @JsonProperty("child_product_id")
    private Long childProductId;
    private BigDecimal quantity;
    private BigDecimal efficiency;
    private String description;
    @JsonProperty("bom_detail_cost")
    private BigDecimal bomDetailCost;
    @JsonProperty("total_bom_detail_cost")
    private BigDecimal totalBomDetailCost;

}
