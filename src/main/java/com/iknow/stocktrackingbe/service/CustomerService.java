package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.thirdparty.Customer;
import com.iknow.stocktrackingbe.payload.request.CustomerRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.AddressRequestMapper;
import com.iknow.stocktrackingbe.payload.request.mapper.CustomerRequestMapper;
import com.iknow.stocktrackingbe.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService{
    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CustomerRequestMapper customerRequestMapper;

    private final AddressRequestMapper addressRequestMapper;

    public List<Customer> getCustomers(Pageable page) {
        logger.info("Service Called: getCustomers");
        return customerRepository.findAll(page).getContent();
    }

    public Customer getCustomerById(Long id) {
        logger.info("Service Called: getCustomerById");
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isPresent()){
            return  optional.get();
        }else {
            logger.error("There is no customer with id:" + id);
            throw new NotFoundException("There is no customer with this id");
        }
    }

    public Customer createCustomer(CustomerRequest customerRequest) {
        logger.info("Service Called: createCustomer");
        if(customerRepository.existsByCustomerName(customerRequest.getCustomerName())&& customerRepository.existsByCustomerCode(customerRequest.getCustomerCode())){
            logger.error("A customer with this name  or code already exists  code: " + customerRequest.getCustomerCode()+" name: " + customerRequest.getCustomerName());
            throw new IllegalStateException("A customer with this name  or code already exists");
        }else{
            Customer customer = customerRequestMapper.mapToModel(customerRequest);
            customerRepository.save(customer);
            return customer;
        }
    }

    public void updateCustomer(Long id, CustomerRequest customerRequest) {
        logger.info("Service Called: updateCustomer");
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isPresent()){
            Customer customer = optional.get();
            customer.setCustomerCode(customerRequest.getCustomerCode()==null ? optional.get().getCustomerCode():customerRequest.getCustomerCode());
            customer.setCustomerName(customerRequest.getCustomerName()==null ? optional.get().getCustomerName():customerRequest.getCustomerName());
            customer.setThirdPartyType(customerRequest.getThirdPartyType()==null ? optional.get().getThirdPartyType():customerRequest.getThirdPartyType());
            customer.setAddress(customerRequest.getAddress()==null ? optional.get().getAddress():addressRequestMapper.mapToModel(customerRequest.getAddress()));
            customer.setEmail(customerRequest.getEmail()==null ? optional.get().getEmail():customerRequest.getEmail());
            customer.setTelNo(customerRequest.getTelNo()==null ? optional.get().getTelNo():customerRequest.getTelNo());
            logger.info("Customer updated");
        }else {
            logger.error("There is no customer with id: " + id);
            throw new NotFoundException("There is no customer with this id");
        }
    }

    public void deleteCustomer(Set<Long> idList) {
        logger.info("Service Called: deleteCustomer");
        customerRepository.deleteByIdIn(idList);
        logger.info("Customers deleted");
    }
}
