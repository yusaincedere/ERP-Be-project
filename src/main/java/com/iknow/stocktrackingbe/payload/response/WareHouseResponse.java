package com.iknow.stocktrackingbe.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Stock;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseResponse {
    private Long id;
    private String name;
    @JsonProperty("parent_name")
    private String parentName;
    @JsonProperty("parent_id")
    private Long parentId;
    @JsonProperty("ware_house_stocks")
    private List<StockResponse> stocks;
    @JsonProperty("address")
    private AddressResponse addressResponse;
}
