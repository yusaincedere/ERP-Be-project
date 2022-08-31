package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.thirdparty.Customer;
import com.iknow.stocktrackingbe.payload.request.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRequestMapper {
    private final AddressRequestMapper addressRequestMapper;
    public Customer mapToModel(CustomerRequest customerRequest) {
        return new Customer().toBuilder()
                .customerName(customerRequest.getCustomerName())
                .address(addressRequestMapper.mapToModel(customerRequest.getAddress()))
                .email(customerRequest.getEmail())
                .telNo(customerRequest.getTelNo())
                .thirdPartyType(customerRequest.getThirdPartyType())
                .customerCode(customerRequest.getCustomerCode())
                .build();
    }
}
