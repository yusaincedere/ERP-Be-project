package com.iknow.stocktrackingbe.repository.product;

import com.iknow.stocktrackingbe.model.product.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient,String> {
    @Transactional
    void deleteByIdIn(ArrayList<String> ids);
}
