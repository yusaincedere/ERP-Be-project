package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.payload.dto.StockCardResponse;
import com.iknow.stocktrackingbe.repository.WareHouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WareHouseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WareHouseRepository wareHouseRepository;
    private final StockCardService stockCardService;



    public WareHouse getWareHouseById(String id){
        Optional<WareHouse> wareHouse = wareHouseRepository.findById(id);
        if(wareHouse.isPresent()){
            return wareHouse.get();
        }else{
            throw new IllegalStateException("");
        }
    }
    public void createWareHouse(WareHouse wareHouse) {
        wareHouseRepository.save(wareHouse);
    }

    public List<StockCardResponse> getStocks(String warehouseId) {
        return stockCardService.findAllByWareHouseId(warehouseId).stream().map(stockCard -> StockCardResponse.builder()
                .expectedSupplyDate(stockCard.getExpectedSupplyDate()).stockCode(stockCard.getStockCode())
                .max(stockCard.getMax()).stockCount(stockCard.getStockCount())
                .name(stockCard.getName())
                .safeStockCount(stockCard.getSafeStockCount())
                .productName(stockCard.getProduct().getProductName())
                .wareHouseName(stockCard.getWareHouse().getName()).build()).collect(Collectors.toList());
    }
}
