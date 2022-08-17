package com.iknow.stocktrackingbe.payload.response.mapper;


import com.iknow.stocktrackingbe.model.product.Dimension;
import com.iknow.stocktrackingbe.model.product.Product;

import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.model.product.Weight;
import com.iknow.stocktrackingbe.payload.response.product.DimensionResponse;
import com.iknow.stocktrackingbe.payload.response.product.ProductResponse;
import com.iknow.stocktrackingbe.payload.response.StockCardResponseProduct;
import com.iknow.stocktrackingbe.payload.response.product.WeightResponse;
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
                .productType(product.getProductType())
                .description(product.getDescription())
                .url(product.getUrl())
                .weight(weightResponseMapper(product.getWeight()))
                .dimension(dimensionResponseMapper(product.getDimension()))
                .build();
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

    public WeightResponse weightResponseMapper(Weight weight){
        return WeightResponse.builder()
                .weightType(weight.getWeightType())
                .amount(weight.getAmount())
                .build();
    }

    public DimensionResponse dimensionResponseMapper(Dimension dimension){
        return DimensionResponse.builder()
                .length(dimension.getLength())
                .dimensionType(dimension.getDimensionType())
                .height(dimension.getHeight())
                .width(dimension.getWidth())
                .build();
    }


}
