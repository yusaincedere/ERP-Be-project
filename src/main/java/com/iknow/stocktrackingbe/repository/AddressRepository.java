package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address,String> {

    Page<Address> findAllByCity(String city, Pageable pageable);


    Page<Address> findAllByCountry(String country,Pageable pageable);

}
