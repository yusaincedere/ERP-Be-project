package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Stock;
import com.iknow.stocktrackingbe.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Stock getStockById(Long id){
        logger.info("Service Called: getStockById");
        Optional<Stock> optional = stockRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            logger.error("There is not stock with  id:" + id);
            throw new NotFoundException("There is not stock with this id");
        }
    }



    public List<Stock> getAllStocksByIdList(Set<Long> idList){
        return stockRepository.findAllById(idList);
    }


    public void delteStock(Stock stock){
        stockRepository.delete(stock);
    }
}
