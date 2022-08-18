package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.request.product.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {

    public Product mapToModel(ProductRequest productRequest) {
        return new Product().toBuilder()
                .productName(productRequest.getProductName())
                .productCode(productRequest.getProductCode())
                .productType(productRequest.getProductType())
                .productUnit(productRequest.getProductUnit())
                .cost(productRequest.getCost())
                .description(productRequest.getDescription())
                .dimensionType(productRequest.getDimensionType())
                .weightType(productRequest.getWeightType())
                .weight(productRequest.getWeight())
                .length(productRequest.getLength())
                .height(productRequest.getHeight())
                .width(productRequest.getWidth())
                .selPrice(productRequest.getSelPrice())
                .toBuy(productRequest.getToBuy())
                .toSell(productRequest.getToSell())
                .url(productRequest.getUrl())
                .build();
    }
}
