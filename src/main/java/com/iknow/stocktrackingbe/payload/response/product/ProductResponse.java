package com.iknow.stocktrackingbe.payload.response.product;
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
public class ProductResponse {
    private Long id;
    @JsonProperty("product_code")
    private String productCode;
    @JsonProperty("name")
    private String productName;

    @JsonProperty("product_type")
    private ProductType productType;

    @JsonProperty("to_buy")
    private Boolean toBuy;
    @JsonProperty("to_sell")
    private Boolean toSell;
    private String description;

    @JsonProperty("sel_price")
    private BigDecimal selPrice;
    private BigDecimal cost;

    private String url;
    @JsonProperty("weight_type")
    private WeightType weightType;

    private BigDecimal weight;

    @JsonProperty("dimension_type")
    private DimensionType dimensionType;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private ProductUnit productUnit;

}
