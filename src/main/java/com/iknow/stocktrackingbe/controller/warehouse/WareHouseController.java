package com.iknow.stocktrackingbe.controller.warehouse;

import com.iknow.stocktrackingbe.service.warehouse.WareHouseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/warehouse")
public class WareHouseController {
    private final WareHouseService wareHouseService;

    public WareHouseController(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }
}
