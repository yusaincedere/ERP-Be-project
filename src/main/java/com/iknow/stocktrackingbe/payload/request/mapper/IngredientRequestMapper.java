package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.model.ProductIngredient;
import com.iknow.stocktrackingbe.payload.request.ProductIngredientRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class IngredientRequestMapper {
    public ProductIngredient mapToModel(ProductIngredientRequest productIngredientRequest, List<Product> productList) {
        return new ProductIngredient().toBuilder()
                .name(productIngredientRequest.getName())
                .stockCount(productIngredientRequest.getStockCount())
                .milliGramWeight(productIngredientRequest.getMilliGramWeight())
                .products(productList)
                .build();
    }
}
