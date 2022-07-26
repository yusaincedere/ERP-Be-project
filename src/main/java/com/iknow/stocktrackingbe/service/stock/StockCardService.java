package com.iknow.stocktrackingbe.service.stock;

import com.iknow.stocktrackingbe.repository.stock.StockCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StockCardService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StockCardRepository stockCardRepository;

    public StockCardService(StockCardRepository stockCardRepository) {
        this.stockCardRepository = stockCardRepository;
    }
}
