package com.iknow.stocktrackingbe.payload.request.mapper;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.payload.request.WareHouseRequest;
import org.springframework.stereotype.Component;

@Component
public class WareHouseRequestMapper {
    public WareHouse mapToModel(WareHouseRequest wareHouseRequest) {
        return new WareHouse().toBuilder()
                .name(wareHouseRequest.getName())
                .build();
    }
}
