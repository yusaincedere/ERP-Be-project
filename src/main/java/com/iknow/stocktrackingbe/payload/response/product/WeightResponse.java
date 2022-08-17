package com.iknow.stocktrackingbe.payload.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.product.WeightType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeightResponse {
    @JsonProperty("weight_type")
    private WeightType weightType;
    private BigDecimal amount;
}
