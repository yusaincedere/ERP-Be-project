package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.payload.request.StockCardRequest;
import org.springframework.stereotype.Component;

@Component
public class StockCardRequestMapper {
    public StockCard mapToModel(StockCardRequest stockCardRequest, Product product, WareHouse wareHouse) {
        return new StockCard().toBuilder()
                .max(stockCardRequest.getMax())
                .name(stockCardRequest.getName())
                .expectedSupplyDate(stockCardRequest.getExpectedSupplyDate())
                .safeStockCount(stockCardRequest.getSafeStockCount())
                .stockCode(stockCardRequest.getStockCode().toUpperCase())
                .stockCount(stockCardRequest.getStockCount())
                .wareHouse(wareHouse)
                .product(product).build();
    }
}
