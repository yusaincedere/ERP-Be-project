package com.iknow.stocktrackingbe.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseResponse {
    private String id;
    private String name;
    @JsonProperty("facility_name")
    private String facilityName;
    @JsonProperty("facility_id")
    private String facilityId;
    @JsonProperty("ware_house_products")
    private List<WareHouseProductResponse> wareHouseProducts;
}
