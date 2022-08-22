package com.iknow.stocktrackingbe.payload.response.mapper;

import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.payload.response.bom.BomListResponse;
import com.iknow.stocktrackingbe.payload.response.bom.BomResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BomListResponseMapper {
    public BomListResponse mapper(Bom bom) {
        return  BomListResponse.builder()
                .bomCode(bom.getBomCode())
                .bomName(bom.getBomName())
                .draft(bom.getDraft())
                .endDate(bom.getEndDate())
                .startDate(bom.getStartDate())
                .mainProductQuantity(bom.getQuantity())
                .mainProductName(bom.getProduct().getProductName())
                .id(bom.getId())
                .build();
    }
    public List<BomListResponse> mapper(List<Bom> bomList) {
        return bomList.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
