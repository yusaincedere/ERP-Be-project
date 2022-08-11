package com.iknow.stocktrackingbe.model.mapper;


import com.iknow.stocktrackingbe.model.Product;

import com.iknow.stocktrackingbe.model.ProductIngredient;
import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.payload.response.ProductIngredientResponseProduct;
import com.iknow.stocktrackingbe.payload.response.ProductResponse;
import com.iknow.stocktrackingbe.payload.response.StockCardResponseProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductResponseMapper {
    public ProductResponse mapper(Product product) {
        List<StockCardResponseProduct> stockCardResponses = this.stockCardMapper(new ArrayList<>(product.getStockCards()));
        List<ProductIngredientResponseProduct> ingredientResponses = this.ingredientMapper(new ArrayList<>(product.getProductIngredients()));
        return  ProductResponse.builder()
                .productName(product.getProductName())
                .id(product.getId())
                .prospectusId(product.getProspectus().getId())
                .productCode(product.getProductCode())
                .productIngredients(ingredientResponses)
                .stockCards(stockCardResponses)
                .expiryDate(product.getExpiryDate())
                .produceDate(product.getProduceDate())
                .amountOfUsage(product.getAmountOfUsage()).build();


    }
    public List<ProductResponse> mapper(List<Product> products) {
        return products.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }

    public ProductIngredientResponseProduct ingredientMapper(ProductIngredient ingredient){
        return ProductIngredientResponseProduct.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .milliGramWeight(ingredient.getMilliGramWeight())
                .build();
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
    public List<ProductIngredientResponseProduct> ingredientMapper(List<ProductIngredient> ingredients){
        return ingredients.stream()
                .map(this::ingredientMapper)
                .collect(Collectors.toList());
    }
}
