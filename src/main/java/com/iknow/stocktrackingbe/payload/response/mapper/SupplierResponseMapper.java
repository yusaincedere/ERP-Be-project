package com.iknow.stocktrackingbe.payload.response.mapper;


import com.iknow.stocktrackingbe.model.thirdparty.Supplier;
import com.iknow.stocktrackingbe.payload.response.thirdparty.SupplierResponse;
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
                .thirdPartyType(supplier.getThirdPartyType())
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
