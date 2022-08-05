package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.payload.request.WareHouseRequest;
import com.iknow.stocktrackingbe.payload.response.StockCardResponse;
import com.iknow.stocktrackingbe.payload.response.WareHouseResponse;
import com.iknow.stocktrackingbe.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/warehouse")
@RequiredArgsConstructor
public class WareHouseController {
    private final WareHouseService wareHouseService;
    @GetMapping(path = "/id/{id}")
    public WareHouseResponse getWareHouseById(@PathVariable  String id){
        return wareHouseService.getWareHouseById(id);
    }
    @GetMapping
    public Page<WareHouseResponse> getWareHouses(Pageable page){
        return wareHouseService.getWareHouses(page);
    }

    @GetMapping(path = "/{warehouseId}/stocks")
    public List<StockCardResponse> getStocks(@PathVariable String warehouseId){
        return  wareHouseService.getStocks(warehouseId);
    }
    @PostMapping
    public void createWareHouse(@RequestBody WareHouseRequest wareHouseRequest){
        wareHouseService.createWareHouse(wareHouseRequest);
    }
}
