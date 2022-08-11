package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductIngredientRequest {
    @NotEmpty
    private String name;
    @JsonProperty("milli_gram_weight")
    private Double milliGramWeight;
    @JsonProperty("stock_count")
    private Long stockCount;
    @JsonProperty("product_ids")
    private Set<String> productIds;
}
