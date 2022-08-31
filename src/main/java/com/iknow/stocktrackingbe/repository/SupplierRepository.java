package com.iknow.stocktrackingbe.repository;


import com.iknow.stocktrackingbe.model.thirdparty.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepository  extends JpaRepository<Supplier,Long> {

    boolean existsBySupplierCodeAndSupplierName(String code,String name);

    void deleteByIdIn(Set<Long> idList);
}
