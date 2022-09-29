package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.payload.request.StockRequest;
import com.iknow.stocktrackingbe.payload.request.WareHouseRequest;
import com.iknow.stocktrackingbe.payload.response.StockResponse;
import com.iknow.stocktrackingbe.payload.response.WareHouseResponse;
import com.iknow.stocktrackingbe.payload.response.mapper.StockResponseMapper;
import com.iknow.stocktrackingbe.payload.response.mapper.WareHouseResponseMapper;
import com.iknow.stocktrackingbe.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/warehouse")
@RequiredArgsConstructor
public class WareHouseController {
    private final WareHouseService wareHouseService;
    private final WareHouseResponseMapper wareHouseResponseMapper;

    private final StockResponseMapper stockResponseMapper;
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<WareHouseResponse> getWareHouseById(@PathVariable  Long id){
        return ResponseEntity.ok(wareHouseResponseMapper.mapper(wareHouseService.getWareHouseById(id)));
    }
    @GetMapping
    public ResponseEntity<List<WareHouseResponse>> getWareHouses(Pageable page){
        return  ResponseEntity.ok(wareHouseResponseMapper.mapper(wareHouseService.getWareHouses(page)));
    }

    @GetMapping(path = "/warehouses/{parentId}")
    public ResponseEntity<List<WareHouseResponse>> getAllChildWarehouses(@PathVariable Long parentId){
        return  ResponseEntity.ok(wareHouseResponseMapper.mapper(wareHouseService.getAllChildWarehouses(parentId)));
    }

    @GetMapping(path = "/stocks/{id}")
    public ResponseEntity<List<StockResponse>> getWareHouseStocks(@PathVariable Long id){
        return  ResponseEntity.ok(stockResponseMapper.mapper(wareHouseService.getWareHouseStocks(id)));
    }
    @PostMapping
    public void createWareHouse(@RequestBody WareHouseRequest wareHouseRequest){
        wareHouseService.createWareHouse(wareHouseRequest);
    }

    @PutMapping("/update/{id}")
    public void updateWareHouse(@PathVariable Long id ,@RequestBody WareHouseRequest wareHouseRequest){
        wareHouseService.updateWareHouse(id,wareHouseRequest);
    }

    @PutMapping("/addStock/{id}")
    public void addStockToWareHouse(@PathVariable Long id ,@RequestBody StockRequest stockRequest){
        wareHouseService.addStockToWareHouse(id,stockRequest);
    }

    @PutMapping("/deleteStock/{wareHoseId}")
    public void deleteStockFromWareHouse(@PathVariable Long wareHoseId ,@RequestParam Set<Long> idListRequest){
        wareHouseService.deleteStockFromWareHouse(wareHoseId,idListRequest);
    }

    @DeleteMapping(path = "/delete")
    public void deleteWareHouses(@RequestParam Set<Long> ids){

        wareHouseService.deleteWareHouses(ids);
    }
}
