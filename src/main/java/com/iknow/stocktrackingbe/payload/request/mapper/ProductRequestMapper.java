package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.model.ProductType;
import com.iknow.stocktrackingbe.model.ProductUnit;
import com.iknow.stocktrackingbe.payload.request.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {

    public Product mapToModel(ProductRequest productRequest, ProductUnit unit, ProductType type) {
        return new Product().toBuilder()
                .productName(productRequest.getProductName())
                .productCode(productRequest.getProductCode())
                .productType(type)
                .productUnit(unit)
                .build();
    }
}
