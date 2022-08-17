package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.payload.request.WareHouseRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.WareHouseRequestMapper;
import com.iknow.stocktrackingbe.payload.response.StockCardResponse;
import com.iknow.stocktrackingbe.repository.WareHouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WareHouseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WareHouseRepository wareHouseRepository;
    private final StockCardService stockCardService;

    private final WareHouseRequestMapper wareHouseRequestMapper;

    public WareHouse getWareHouseById(String id){
        logger.info("Service Called: getWareHouseById");
        Optional<WareHouse> optional = wareHouseRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new IllegalStateException("");
        }
    }

    public List<WareHouse> getWareHouses(Pageable page){
        Page<WareHouse> wareHousesPage = wareHouseRepository.findAll(page);
        if(!wareHousesPage.getContent().isEmpty()){
          return wareHousesPage.getContent();
        }else {
            throw new NotFoundException("There is no warehouse");
        }
    }
    public void createWareHouse(WareHouseRequest wareHouseRequest) {
        logger.info("Service Called: createWareHouse");
        WareHouse wareHouse = wareHouseRequestMapper.mapToModel(wareHouseRequest);
        wareHouseRepository.save(wareHouse);
    }

    public List<StockCardResponse> getStocks(String warehouseId) {
        logger.info("Service Called: getStocks");
        return stockCardService.findAllByWareHouseId(warehouseId).stream().map(stockCard -> StockCardResponse.builder()
                .expectedSupplyDate(stockCard.getExpectedSupplyDate()).stockCode(stockCard.getStockCode())
                .max(stockCard.getMax()).stockCount(stockCard.getStockCount())
                .name(stockCard.getName())
                .safeStockCount(stockCard.getSafeStockCount())
                .productName(stockCard.getProduct().getProductName())
                .wareHouseName(stockCard.getWareHouse().getName()).build()).collect(Collectors.toList());
    }

    public List<WareHouse> getWareHosesByIds(Set<String> wareHouseIds) {
        logger.info("Service Called: getWareHosesByIds");
        return wareHouseRepository.findAllById(wareHouseIds);
    }
}
