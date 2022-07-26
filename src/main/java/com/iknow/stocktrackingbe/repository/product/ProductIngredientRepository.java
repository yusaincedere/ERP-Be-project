package com.iknow.stocktrackingbe.repository.product;

import com.iknow.stocktrackingbe.model.product.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient,String> {
}
