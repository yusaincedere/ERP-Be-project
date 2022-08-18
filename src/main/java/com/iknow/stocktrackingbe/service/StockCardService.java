package com.iknow.stocktrackingbe.service;


import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.model.StockCardStatus;
import com.iknow.stocktrackingbe.repository.StockCardRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StockCardService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StockCardRepository stockCardRepository;


    public Set<StockCard> findAllByWareHouseId(Long id) {
        logger.info("Service called: findAllByWareHouseId");
        return stockCardRepository.findAllByWareHouseId(id);
    }

    public List<StockCard> getStockCardByWareHouseId(Long warehouseId, Pageable pageable) {
        logger.info("Service called: getStockCardByWareHouseId");
        Page<StockCard> stockCardPage = stockCardRepository.findAllByWareHouseId(warehouseId,pageable);
        return stockCardPage.getContent();
    }

    public List<StockCard> getStockCardByCountry(String country,Pageable pageable) {
        logger.info("Service called: getStockCardByWareHouseId");
        Page<StockCard> stockCardPage = stockCardRepository.findAllByWareHouseAddressCountry(country,pageable);
        return stockCardPage.getContent();
    }

    public List<StockCard> getStockCardByCity(String city,Pageable pageable) {
        logger.info("Service called: getStockCardByWareHouseId");
        Page<StockCard> stockCardPage = stockCardRepository.findAllByWareHouseAddressCity(city,pageable);
        return stockCardPage.getContent();
    }



    public StockCard getStockCardById(Long id) {
        logger.info("Service called: getStockCardById");
        Optional<StockCard> optional = stockCardRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new IllegalStateException("There is no stock card with this id");
        }
    }



    public StockCard approveStockCard(Long id) {
        logger.info("Service called: getStockCardById");
        Optional<StockCard> optional = stockCardRepository.findById(id);
        if (optional.isPresent()) {
            StockCard stockCard = optional.get();
            stockCard.setStockCardStatus(StockCardStatus.FINISHED);
            stockCardRepository.flush();
            return stockCard;

        } else {
            throw new IllegalStateException("There is no stock card with this id");
        }
    }
}
