package com.iknow.stocktrackingbe.payload.request.mapper;

import com.iknow.stocktrackingbe.model.*;
import com.iknow.stocktrackingbe.payload.request.BomRequest;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BomRequestMapper {

    public Bom mapToModel(BomRequest bomRequest,Product product) {
        return new Bom().toBuilder()
                .bomCode(bomRequest.getBomCode())
                .endDate(bomRequest.getEndDate())
                .startDate(bomRequest.getStartDate())
                .product(product)
                .build();
    }
}
