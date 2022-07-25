package com.iknow.stocktrackingbe.service.warehouse;

import com.iknow.stocktrackingbe.repository.warehouse.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
