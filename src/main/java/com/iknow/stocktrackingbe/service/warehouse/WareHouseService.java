package com.iknow.stocktrackingbe.service.warehouse;

import com.iknow.stocktrackingbe.repository.warehouse.WareHouseRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class WareHouseService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final WareHouseRepository wareHouseRepository;

    public WareHouseService(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }
}
