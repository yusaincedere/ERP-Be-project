package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.repository.StockCardRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockCardService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StockCardRepository stockCardRepository;


    public List<StockCard> findAllByWareHouseId(String id) {
        return stockCardRepository.findAllByWareHouseId(id);
    }
}
