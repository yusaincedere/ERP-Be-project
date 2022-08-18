package com.iknow.stocktrackingbe.payload.request.bom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomDetailRequest{

    @JsonProperty("child_product_id")
    private Long childProductId;
    private BigDecimal efficiency;
    private Long quantity;
    private String description;
}
