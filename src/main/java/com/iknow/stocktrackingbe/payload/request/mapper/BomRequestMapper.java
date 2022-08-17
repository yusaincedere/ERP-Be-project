package com.iknow.stocktrackingbe.payload.request.mapper;

import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.request.bom.BomRequest;

import org.springframework.stereotype.Component;

@Component
public class BomRequestMapper {

    public Bom mapToModel(BomRequest bomRequest, Product product, WareHouse wareHouse) {
        return new Bom().toBuilder()
                .description(bomRequest.getDescription())
                .efficiency(bomRequest.getEfficiency())
                .quantity(bomRequest.getQuantity())
                .wareHouse(wareHouse)
                .bomCode(bomRequest.getBomCode())
                .endDate(bomRequest.getEndDate())
                .startDate(bomRequest.getStartDate())
                .product(product)
                .build();
    }
}
