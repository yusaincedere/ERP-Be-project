package com.iknow.stocktrackingbe.payload.request.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.product.Dimension;
import com.iknow.stocktrackingbe.model.product.ProductType;
import com.iknow.stocktrackingbe.model.product.ProductUnit;
import com.iknow.stocktrackingbe.model.product.Weight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequest {
    @NotNull
    @JsonProperty("name")
    private String productName;
    @NotNull
    @JsonProperty("product_code")
    private String productCode;
    @NotNull
    @JsonProperty("product_type")
    private ProductType productType;
    @NotNull
    @JsonProperty("product_unit")
    private ProductUnit productUnit;


    private String description;

    private String url;

    @JsonProperty("sel_price")
    private BigDecimal selPrice;

    private BigDecimal cost;

    private DimensionRequest dimension;

    private WeightRequest weight;

    @JsonProperty("to_buy")
    private Boolean toBuy;
    @JsonProperty("to_sell")
    private Boolean toSell;
}
