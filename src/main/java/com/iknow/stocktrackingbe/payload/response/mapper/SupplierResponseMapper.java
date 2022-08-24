package com.iknow.stocktrackingbe.payload.response.mapper;


import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.suplier.Supplier;
import com.iknow.stocktrackingbe.payload.response.bom.BomResponse;
import com.iknow.stocktrackingbe.payload.response.supplier.SupplierResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SupplierResponseMapper {
    private final AddressResponseMapper addressResponseMapper;
    public SupplierResponse mapper(Supplier supplier) {
        return  SupplierResponse.builder()
                .addressResponse(addressResponseMapper.mapper(supplier.getAddress()))
                .id(supplier.getId())
                .supplierCode(supplier.getSupplierCode())
                .supplierName(supplier.getSupplierName())
                .supplierType(supplier.getSupplierType())
                .email(supplier.getEmail())
                .telNo(supplier.getTelNo())
                .build();
    }
    public List<SupplierResponse> mapper(List<Supplier> suppliers) {
        return suppliers.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
