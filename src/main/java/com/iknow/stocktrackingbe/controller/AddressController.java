package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/address")
@RequiredArgsConstructor
public class AddressController {



    private final AddressService addressService;



    @GetMapping(path = "/country/{country}")
    public ResponseEntity<List<Address>> getAddressesByCountry(@PathVariable String country, Pageable pageable){
        return  ResponseEntity.ok(addressService.getAddressesByCountry(country,pageable));
    }

    @GetMapping(path = "/city/{city}")
    public ResponseEntity<List<Address>> getAddressesByCity(@PathVariable String city, Pageable pageable){
        return  ResponseEntity.ok(addressService.getAddressesByCity(city,pageable));
    }
}
