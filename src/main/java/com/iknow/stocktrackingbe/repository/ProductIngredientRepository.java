package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.ProductIngredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient,String> {

    void deleteByIdIn(Set<String> ids);

    Page<ProductIngredient> findAllByNameContainingIgnoreCase(String name, Pageable page);


    boolean existsByName(String name);
}
