package com.iknow.stocktrackingbe.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseProductResponse {
    private String id;
    private String name;
}
