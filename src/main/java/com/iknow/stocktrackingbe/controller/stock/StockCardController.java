package com.iknow.stocktrackingbe.controller.stock;


import com.iknow.stocktrackingbe.service.stock.StockCardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stockCard")
public class StockCardController {
    private final StockCardService stockCardService;

    public StockCardController(StockCardService stockCardService) {
        this.stockCardService = stockCardService;
    }
}
