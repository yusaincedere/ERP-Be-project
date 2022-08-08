package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.payload.request.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {

    public Product mapToModel(ProductRequest productRequest) {
        return new Product().toBuilder()
                .productName(productRequest.getProductName())
                .expiryDate(productRequest.getExpiryDate())
                .price(productRequest.getPrice())
                .produceDate(productRequest.getProduceDate())
                .productCode(productRequest.getProductCode())
                .amountOfUsage(productRequest.getAmountOfUsage())
                .prospectus(productRequest.getProspectus())
                .build();
    }
}
