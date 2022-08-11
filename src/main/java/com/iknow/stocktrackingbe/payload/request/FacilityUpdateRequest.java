package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.WareHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityUpdateRequest {
    private String name;
    @JsonProperty("ware_house_ids")
    private Set<String> wareHouseIds =new HashSet<>();
    private Address address;
}
