package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.model.WareHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface WareHouseRepository extends JpaRepository<WareHouse,Long> {

    Page<WareHouse> findAllByNameContainingIgnoreCase(String name, Pageable pageable);


    
    List<WareHouse> findAllByParentId(Long parentId);

    void deleteByIdIn(Set<Long> ids);
}
