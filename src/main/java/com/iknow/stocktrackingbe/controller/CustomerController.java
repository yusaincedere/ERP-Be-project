package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.payload.request.CustomerRequest;
import com.iknow.stocktrackingbe.payload.response.mapper.CustomerListResponseMapper;
import com.iknow.stocktrackingbe.payload.response.mapper.CustomerResponseMapper;
import com.iknow.stocktrackingbe.payload.response.thirdparty.CustomerListResponse;
import com.iknow.stocktrackingbe.payload.response.thirdparty.CustomerResponse;
import com.iknow.stocktrackingbe.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerListResponseMapper customerListResponseMapper;
    private final CustomerResponseMapper customerResponseMapper;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerListResponse>> getCustomers(Pageable page){
        return ResponseEntity.ok(customerListResponseMapper.mapper(customerService.getCustomers(page)));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CustomerResponse>getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerResponseMapper.mapper(customerService.getCustomerById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok(customerResponseMapper.mapper(customerService.createCustomer(customerRequest)));

    }

    @PutMapping("/{id}/update")
    public void updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest customerRequest){
        customerService.updateCustomer(id,customerRequest);
    }

    @DeleteMapping(path = "/delete")
    public void deleteCustomer(@RequestParam Set<Long> idList){
        customerService.deleteCustomer(idList);
    }
}
