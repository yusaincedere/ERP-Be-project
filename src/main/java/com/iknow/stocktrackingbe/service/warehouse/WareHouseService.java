package com.iknow.stocktrackingbe.service.warehouse;

import com.iknow.stocktrackingbe.repository.warehouse.WareHouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WareHouseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WareHouseRepository wareHouseRepository;

    public WareHouseService(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }
}
