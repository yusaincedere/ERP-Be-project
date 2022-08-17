package com.iknow.stocktrackingbe.payload.response.mapper;

import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.payload.response.bom.BomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BomResponseMapper {

    private final BomDetailResponseMapper bomDetailResponseMapper;

    public BomResponse mapper(Bom bom) {
        return  BomResponse.builder()
                .bomCode(bom.getBomCode())
                .bomName(bom.getBomName())
                .approved(bom.getApproved())
                .draft(bom.getDraft())
                .endDate(bom.getEndDate())
                .startDate(bom.getStartDate())
                .bomDetailResponses(bomDetailResponseMapper.mapper(bom.getBomDetails()))
                .description(bom.getDescription())
                .efficiency(bom.getEfficiency())
                .mainProductQuantity(bom.getQuantity())
                .mainProductName(bom.getProduct().getProductName())
                .id(bom.getId())
                .build();
    }
    public List<BomResponse> mapper(List<Bom> bomList) {
        return bomList.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
