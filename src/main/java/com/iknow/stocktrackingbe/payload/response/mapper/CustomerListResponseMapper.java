package com.iknow.stocktrackingbe.payload.response.mapper;
import com.iknow.stocktrackingbe.model.thirdparty.Customer;
import com.iknow.stocktrackingbe.payload.response.thirdparty.CustomerListResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerListResponseMapper {
    public CustomerListResponse mapper(Customer customer) {
        return  CustomerListResponse.builder()
                .customerCode(customer.getCustomerCode())
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .email(customer.getEmail())
                .telNo(customer.getTelNo())
                .build();
    }
    public List<CustomerListResponse> mapper(List<Customer> customers) {
        return customers.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }
}
