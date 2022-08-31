package com.iknow.stocktrackingbe.payload.response.mapper;

import com.iknow.stocktrackingbe.model.thirdparty.Customer;
import com.iknow.stocktrackingbe.model.thirdparty.Supplier;
import com.iknow.stocktrackingbe.payload.response.thirdparty.CustomerResponse;
import com.iknow.stocktrackingbe.payload.response.thirdparty.SupplierResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerResponseMapper {
    private final AddressResponseMapper addressResponseMapper;
    public CustomerResponse mapper(Customer customer) {
        return  CustomerResponse.builder()
                .customerName(customer.getCustomerName())
                .email(customer.getEmail())
                .telNo(customer.getTelNo())
                .thirdPartyType(customer.getThirdPartyType())
                .address(addressResponseMapper.mapper(customer.getAddress()))
                .customerCode(customer.getCustomerCode())
                .build();
    }
    public List<CustomerResponse> mapper(List<Customer> customers) {
        return customers.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
