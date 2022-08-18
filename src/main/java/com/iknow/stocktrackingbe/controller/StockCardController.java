package com.iknow.stocktrackingbe.controller;


import com.iknow.stocktrackingbe.model.StockCard;
import com.iknow.stocktrackingbe.service.StockCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/stockCard")
@RequiredArgsConstructor
public class StockCardController {
    private final StockCardService stockCardService;



    @GetMapping("/id/{id}")
    public ResponseEntity<StockCard> getStockCardById(@PathVariable Long id){
        return ResponseEntity.ok(stockCardService.getStockCardById(id));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<StockCard>> getStockCardByCountry(@PathVariable String country, Pageable pageable){
        return ResponseEntity.ok(stockCardService.getStockCardByCountry(country,pageable));
    }
    @GetMapping("/city/{city}")
    public ResponseEntity<List<StockCard>> getStockCardByCity(@PathVariable String city, Pageable pageable){
        return ResponseEntity.ok(stockCardService.getStockCardByCity(city,pageable));
    }

    @GetMapping("/warehouse/{id}")
    public ResponseEntity<List<StockCard>> getStockCardByWareHouseId(@PathVariable Long id,Pageable pageable){
        return ResponseEntity.ok(stockCardService.getStockCardByWareHouseId(id,pageable));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<StockCard> approveStockCard(@PathVariable Long id){
        return ResponseEntity.ok(stockCardService.approveStockCard(id));
    }

}
