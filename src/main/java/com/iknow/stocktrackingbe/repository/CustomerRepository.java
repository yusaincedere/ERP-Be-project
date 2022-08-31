package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.thirdparty.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {

    boolean existsByCustomerName(String customerName);
    boolean existsByCustomerCode(String customerCode);

    void deleteByIdIn(Set<Long> idList);
}

