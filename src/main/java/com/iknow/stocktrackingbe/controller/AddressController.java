package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/address")
public class AddressController {



    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping(path = "/byCountry")
    public List<Address> getAddressesByCountry(@PathVariable String ct){
        return  addressService.getAddressesByCountry(ct);
    }



}
