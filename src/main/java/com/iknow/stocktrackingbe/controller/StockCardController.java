package com.iknow.stocktrackingbe.controller;


import com.iknow.stocktrackingbe.service.StockCardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/stockCard")
public class StockCardController {
    private final StockCardService stockCardService;

    public StockCardController(StockCardService stockCardService) {
        this.stockCardService = stockCardService;
    }
}
