package com.iknow.stocktrackingbe.payload.response.mapper;

import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.response.WareHouseResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WareHouseResponseMapper {

    private final StockResponseMapper stockResponseMapper;
    public WareHouseResponse mapper(WareHouse wareHouse) {
        return  WareHouseResponse.builder()
                .id(wareHouse.getId())
                .stocks(stockResponseMapper.mapper(wareHouse.getStocks()))
                .name(wareHouse.getName())
                .parentId(wareHouse.getParent()==null ? null:wareHouse.getParent().getId())
                .parentName(wareHouse.getParent()==null ? null:wareHouse.getParent().getName())
                .build();
    }


    public List<WareHouseResponse> mapper(List<WareHouse> wareHouses){
        return wareHouses.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
