package com.iknow.stocktrackingbe.model.mapper;


import com.iknow.stocktrackingbe.model.Product;

import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.payload.response.ProductResponse;
import com.iknow.stocktrackingbe.payload.response.StockCardResponseProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductResponseMapper {
    public ProductResponse mapper(Product product) {
        return  ProductResponse.builder()
                .productName(product.getProductName())
                .id(product.getId())
                .productCode(product.getProductCode())
                .productType(product.getProductType().getType())
                .productUnit(product.getProductUnit().getUnitName()).build();
    }
    public List<ProductResponse> mapper(List<Product> products) {
        return products.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }

    public StockCardResponseProduct stockCardMapper(StockCard stockCard){
            return StockCardResponseProduct.builder()
                    .stockCount(stockCard.getStockCount())
                    .stockCode(stockCard.getStockCode())
                    .id(stockCard.getId())
                    .build();
    }

    public List<StockCardResponseProduct> stockCardMapper(List<StockCard> stockCards){
        return stockCards.stream()
                .map(this::stockCardMapper)
                .collect(Collectors.toList());
    }
}
