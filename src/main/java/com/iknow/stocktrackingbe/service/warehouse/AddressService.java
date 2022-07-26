package com.iknow.stocktrackingbe.service.warehouse;

import com.iknow.stocktrackingbe.repository.warehouse.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
