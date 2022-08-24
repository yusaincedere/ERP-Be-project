package com.iknow.stocktrackingbe.payload.request.mapper;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.payload.request.WareHouseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WareHouseRequestMapper {
    private final AddressRequestMapper addressRequestMapper;
    public WareHouse mapToModel(WareHouseRequest wareHouseRequest) {
        return new WareHouse().toBuilder()
                .name(wareHouseRequest.getName())
                .address(addressRequestMapper.mapToModel(wareHouseRequest.getAddress()))
                .phone(wareHouseRequest.getPhone())
                .build();
    }
}
