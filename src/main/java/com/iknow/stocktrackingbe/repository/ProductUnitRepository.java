package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.ProductUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Repository
@Transactional
public interface ProductUnitRepository extends JpaRepository<ProductUnit,String> {
    Optional<ProductUnit> findByUnitName(String unit);

    boolean existsByUnitName(String unitName);

    void deleteByIdIn(Set<String> ids);
}
