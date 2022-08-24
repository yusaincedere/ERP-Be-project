package com.iknow.stocktrackingbe.payload.response.mapper;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.Stock;
import com.iknow.stocktrackingbe.payload.response.AddressResponse;
import com.iknow.stocktrackingbe.payload.response.StockResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressResponseMapper {
    public AddressResponse mapper(Address address) {
        return  AddressResponse.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .street(address.getStreet())
                .district(address.getDistrict())
                .postCode(address.getPostCode())
                .build();
    }


    public List<AddressResponse> mapper(List<Address> addresses){
        return addresses.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
