package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.model.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomDetailRequest{

    @JsonProperty("child_product_id")
    private String childProductId;
    @JsonProperty("child_amount")
    private Long childAmount;
    @JsonProperty("main_amount")
    private Long mainAmount;
}
