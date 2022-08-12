package com.iknow.stocktrackingbe.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
    private String productType;
    @NotNull
    @JsonProperty("product_unit")
    private String productUnit;
}
