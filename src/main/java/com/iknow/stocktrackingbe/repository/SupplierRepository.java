package com.iknow.stocktrackingbe.repository;


import com.iknow.stocktrackingbe.model.suplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository  extends JpaRepository<Supplier,Long> {

    boolean existsBySupplierCodeAndSupplierName(String code,String name);
}
