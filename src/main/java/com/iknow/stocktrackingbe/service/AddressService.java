package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AddressRepository addressRepository;




    public List<Address> getAddressesByCity(String city, Pageable pageable){
        Page<Address> addressesPage = addressRepository.findAllByCity(city,pageable);
        return addressesPage.getContent();
    }

    public List<Address> getAddressesByCountry(String country,Pageable pageable) {
        Page<Address> addressesPage = addressRepository.findAllByCountry(country,pageable);
        return addressesPage.getContent();
    }
}
