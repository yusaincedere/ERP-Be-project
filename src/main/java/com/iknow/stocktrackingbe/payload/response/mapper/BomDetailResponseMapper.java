package com.iknow.stocktrackingbe.payload.response.mapper;

import com.iknow.stocktrackingbe.model.bom.BomDetail;
import com.iknow.stocktrackingbe.payload.response.bom.BomDetailResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BomDetailResponseMapper {

    public BomDetailResponse mapper(BomDetail bomDetail) {
        return  BomDetailResponse.builder()
                .bomDetailId(bomDetail.getId())
                .quantity(bomDetail.getQuantity())
                .childProductId(bomDetail.getChildProduct().getId())
                .description(bomDetail.getDescription())
                .efficiency(bomDetail.getEfficiency())
                .childProductName(bomDetail.getChildProduct().getProductName())
                .bomDetailCost(bomDetail.getBomDetailCost())
                .totalBomDetailCost(bomDetail.getTotalBomDetailCost())
                .build();
    }
    public List<BomDetailResponse> mapper(List<BomDetail> bomDetails) {
        return bomDetails.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
