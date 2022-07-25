package com.iknow.stocktrackingbe.service.stock;

import com.iknow.stocktrackingbe.repository.stock.StockCardRepository;
import org.springframework.stereotype.Service;

@Service
public class StockCardService {
    private final StockCardRepository stockCardRepository;

    public StockCardService(StockCardRepository stockCardRepository) {
        this.stockCardRepository = stockCardRepository;
    }
}
