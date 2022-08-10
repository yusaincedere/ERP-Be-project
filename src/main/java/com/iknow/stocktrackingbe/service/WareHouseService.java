package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Facility;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.payload.request.WareHouseRequest;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.payload.response.StockCardResponse;
import com.iknow.stocktrackingbe.payload.response.WareHouseProductResponse;
import com.iknow.stocktrackingbe.payload.response.WareHouseResponse;
import com.iknow.stocktrackingbe.repository.FacilityRepository;
import com.iknow.stocktrackingbe.repository.WareHouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private final FacilityRepository facilityRepository;

    public WareHouseResponse getWareHouseById(String id){
        logger.info("Service Called: getWareHouseById");
        Optional<WareHouse> optional = wareHouseRepository.findById(id);
        if(optional.isPresent()){
            WareHouse wareHouse =  optional.get();
            return WareHouseResponse.builder()
                    .id(wareHouse.getId())
                    .facilityId(wareHouse.getFacility().getId())
                    .name(wareHouse.getName())
                    .facilityName(wareHouse.getFacility().getName())
                    .wareHouseProducts(
                            wareHouse.getProducts().stream().map(
                               product -> WareHouseProductResponse.builder()
                                       .id(product.getId())
                                       .name(product.getProductName()).build()
                            ).collect(Collectors.toList())
                    ).build();
        }else{
            throw new IllegalStateException("");
        }
    }

    public Page<WareHouseResponse> getWareHouses(Pageable page){
        Page<WareHouse> wareHousesPage = wareHouseRepository.findAll(page);
        if(!wareHousesPage.getContent().isEmpty()){
            int totalElements =  wareHousesPage.getNumberOfElements();
            return new PageImpl<WareHouseResponse>(wareHousesPage.getContent()
                    .stream().map(wareHouse -> WareHouseResponse.builder()
                            .name(wareHouse.getName())
                            .facilityId(wareHouse.getFacility().getId())
                            .facilityName(wareHouse.getFacility().getName())
                            .wareHouseProducts(
                                    wareHouse.getProducts().stream().map(
                                            product -> WareHouseProductResponse.builder()
                                                    .id(product.getId())
                                                    .name(product.getProductName()).build()
                                    ).collect(Collectors.toList())
                            )
                            .build()).collect(Collectors.toList()),page,totalElements);
        }else {
            throw new NotFoundException("There is no Prescription");
        }
    }
    public void createWareHouse(WareHouseRequest wareHouseRequest) {
        logger.info("Service Called: createWareHouse");
        Facility facility = facilityRepository.findById(wareHouseRequest.getFacilityId()).orElseThrow(()->new IllegalStateException("No facility with this id"));
        WareHouse wareHouse = new WareHouse().toBuilder()
                .facility(facility).address(facility.getAddress()).name(wareHouseRequest.getName())
                .build();
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

    public List<WareHouse> getWareHosesByIds(List<String> wareHouseIds) {
        logger.info("Service Called: getWareHosesByIds");
        return wareHouseRepository.findAllById(wareHouseIds);
    }
}
