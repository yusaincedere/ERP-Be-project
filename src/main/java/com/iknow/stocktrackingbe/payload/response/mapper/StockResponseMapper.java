package com.iknow.stocktrackingbe.payload.response.mapper;
import com.iknow.stocktrackingbe.model.Stock;
import com.iknow.stocktrackingbe.payload.response.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockResponseMapper {

    public StockResponse mapper(Stock stock) {
        return  StockResponse.builder()
                .stockId(stock.getId())
                .productName(stock.getProduct().getProductName())
                .orderQuantity(stock.getOrderQuantity())
                .minStock(stock.getMinStock())
                .productId(stock.getProduct().getId())
                .stock(stock.getStock())
                .build();
    }


    public List<StockResponse> mapper(List<Stock> stocks){
        return stocks.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }

}
