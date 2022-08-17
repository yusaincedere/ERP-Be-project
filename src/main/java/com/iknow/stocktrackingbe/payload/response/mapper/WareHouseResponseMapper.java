package com.iknow.stocktrackingbe.payload.response.mapper;

import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.response.WareHouseProductResponse;
import com.iknow.stocktrackingbe.payload.response.WareHouseResponse;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WareHouseResponseMapper {

    public WareHouseResponse mapper(WareHouse wareHouse) {
        return  WareHouseResponse.builder()
                .id(wareHouse.getId())
                .wareHouseProducts(toResponse(wareHouse.getProducts()))
                .name(wareHouse.getName())
                .build();
    }


    public WareHouseProductResponse toResponse(Product product){
        return WareHouseProductResponse.builder()
                .name(product.getProductName())
                .id(product.getId())
                .build();
    }

    public List<WareHouseProductResponse> toResponse(List<Product> products){
        return products.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<WareHouseResponse> mapper(List<WareHouse> wareHouses){
        return wareHouses.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
