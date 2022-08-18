package com.iknow.stocktrackingbe.payload.request.mapper;

import com.iknow.stocktrackingbe.model.Stock;
import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.request.StockCardRequest;
import com.iknow.stocktrackingbe.payload.request.StockRequest;
import org.springframework.stereotype.Component;

@Component
public class StockRequestMapper {
    public Stock mapToModel(StockRequest stockRequest,WareHouse wareHouse,Product product) {
        return new Stock().toBuilder()
                .stock(stockRequest.getStock())
                .minStock(stockRequest.getMinStock())
                .orderQuantity(stockRequest.getOrderQuantity())
                .product(product)
                .wareHouse(wareHouse)
                .build();
    }
}
