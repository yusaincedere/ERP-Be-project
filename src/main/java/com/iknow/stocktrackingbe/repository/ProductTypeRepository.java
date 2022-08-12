package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface ProductTypeRepository extends JpaRepository<ProductType,String> {

    Optional<ProductType> findByType(String type);

    boolean existsByType(String type);

    void deleteByIdIn(Set<String> ids);
}
