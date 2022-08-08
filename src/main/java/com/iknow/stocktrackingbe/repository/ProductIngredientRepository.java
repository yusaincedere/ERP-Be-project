package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient,String> {

    void deleteByIdIn(List<String> ids);
}
