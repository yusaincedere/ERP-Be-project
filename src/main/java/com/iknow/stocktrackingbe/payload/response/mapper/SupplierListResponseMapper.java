package com.iknow.stocktrackingbe.payload.response.mapper;
import com.iknow.stocktrackingbe.model.thirdparty.Supplier;
import com.iknow.stocktrackingbe.payload.response.thirdparty.SupplierListResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierListResponseMapper {
    public SupplierListResponse mapper(Supplier supplier) {
        return  SupplierListResponse.builder()
                .supplierCode(supplier.getSupplierCode())
                .supplierName(supplier.getSupplierName())
                .telNo(supplier.getTelNo())
                .build();
    }
    public List<SupplierListResponse> mapper(List<Supplier> suppliers) {
        return suppliers.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
