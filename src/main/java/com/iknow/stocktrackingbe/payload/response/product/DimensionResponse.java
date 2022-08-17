package com.iknow.stocktrackingbe.payload.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.product.DimensionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DimensionResponse{
    @JsonProperty("dimension_type")
    private DimensionType dimensionType;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal width;
}
