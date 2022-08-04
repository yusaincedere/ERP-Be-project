package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address,String> {

    List<Address> findAllByCity(String city);


    List<Address> findAllByCountry(String country);

}
