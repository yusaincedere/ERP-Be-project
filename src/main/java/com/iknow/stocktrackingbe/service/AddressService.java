package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AddressRepository addressRepository;




    public List<Address> getAddressByCity(String city){
        return addressRepository.findAllByCity(city);
    }

    public List<Address> getAddresByCountry(String ct) {
        return  addressRepository.findAllByCountry(ct);
    }
}
