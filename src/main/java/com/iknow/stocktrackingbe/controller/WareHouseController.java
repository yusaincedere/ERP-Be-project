package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.payload.dto.StockCardResponse;
import com.iknow.stocktrackingbe.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/warehouse")
@RequiredArgsConstructor
public class WareHouseController {
    private final WareHouseService wareHouseService;



    @PostMapping
    public void createWareHouse(@RequestBody WareHouse wareHouse){
        System.out.println(wareHouse.getName());
        wareHouseService.createWareHouse(wareHouse);
    }

    @GetMapping(path = "/{warehouseId}/stocks")
    public List<StockCardResponse> getStocks(@PathVariable String warehouseId){
        return  wareHouseService.getStocks(warehouseId);
    }
}
