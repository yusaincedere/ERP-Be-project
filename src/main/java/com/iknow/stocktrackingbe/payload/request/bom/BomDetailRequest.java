package com.iknow.stocktrackingbe.payload.request.bom;

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
public class BomDetailRequest{

    @JsonProperty("child_product_id")
    private String childProductId;
    private BigDecimal efficiency;
    private Long quantity;
    private String description;
}
