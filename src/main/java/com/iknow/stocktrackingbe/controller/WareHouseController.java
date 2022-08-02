package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.service.WareHouseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/aoi/warehouse")
public class WareHouseController {
    private final WareHouseService wareHouseService;

    public WareHouseController(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }
}
