package com.iknow.stocktrackingbe.payload.response.mapper;

import com.iknow.stocktrackingbe.model.Bom;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.payload.response.BomResponse;
import com.iknow.stocktrackingbe.payload.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BomResponseMapper {

    public BomResponse mapper(Bom bom) {
        return  BomResponse.builder()
                .bomCode(bom.getBomCode())
                .bomName(bom.getBomName())
                .approved(bom.isApproved())
                .draft(bom.isDraft())
                .endDate(bom.getEndDate())
                .startDate(bom.getStartDate())
                .build();
    }
    public List<BomResponse> mapper(List<Bom> bomList) {
        return bomList.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
