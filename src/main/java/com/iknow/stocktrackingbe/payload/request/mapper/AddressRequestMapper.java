package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.payload.request.AddressRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressRequestMapper {
    public Address mapToModel(AddressRequest addressRequest) {
        return new Address().toBuilder()
                .city(addressRequest.getCity())
                .country(addressRequest.getCountry())
                .details(addressRequest.getDetails())
                .district(addressRequest.getDistrict())
                .postCode(addressRequest.getPostCode())
                .street(addressRequest.getStreet())
                .build();
    }
}
