package com.iknow.stocktrackingbe.payload.request.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.product.DimensionType;
import com.iknow.stocktrackingbe.model.product.ProductType;
import com.iknow.stocktrackingbe.model.product.ProductUnit;
import com.iknow.stocktrackingbe.model.product.WeightType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequest {

    @JsonProperty("name")
    private String productName;
    @JsonProperty("product_code")
    private String productCode;
    @JsonProperty("product_type")
    private ProductType productType;
    @JsonProperty("product_unit")
    private ProductUnit productUnit;


    private String description;

    private String url;

    @JsonProperty("sel_price")
    private BigDecimal selPrice;

    private BigDecimal cost;

    @JsonProperty("dimension_type")
    private DimensionType dimensionType;
    @JsonProperty("weight_type")
    private WeightType weightType;
    private BigDecimal weight;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;

    @JsonProperty("to_buy")
    private Boolean toBuy;
    @JsonProperty("to_sell")
    private Boolean toSell;
}
