package com.iknow.stocktrackingbe.service.warehouse;

import com.iknow.stocktrackingbe.repository.warehouse.WareHouseRepository;
import org.springframework.stereotype.Service;

@Service
public class WareHouseService {
    private final WareHouseRepository wareHouseRepository;

    public WareHouseService(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }
}
