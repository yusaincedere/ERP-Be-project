package com.iknow.stocktrackingbe.payload.response.product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.product.Dimension;
import com.iknow.stocktrackingbe.model.product.ProductType;
import com.iknow.stocktrackingbe.model.product.Weight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    @JsonProperty("product_code")
    private String productCode;
    @JsonProperty("name")
    private String productName;

    @JsonProperty("product_type")
    private ProductType productType;


    private String description;

    private String url;

    private WeightResponse weight;

    private DimensionResponse dimension;


}
