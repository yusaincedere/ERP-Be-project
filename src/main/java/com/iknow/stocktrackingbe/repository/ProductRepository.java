package com.iknow.stocktrackingbe.repository;
import com.iknow.stocktrackingbe.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    void deleteByIdIn(Set<Long> ids);

    Page<Product> findAllByProductNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByProductName(String name);


    List<Product> findAllByIdIn(List<Long> ids);

}
