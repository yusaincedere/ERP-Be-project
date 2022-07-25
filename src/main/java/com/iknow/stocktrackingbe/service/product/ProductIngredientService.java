package com.iknow.stocktrackingbe.service.product;

import com.iknow.stocktrackingbe.repository.product.ProductIngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductIngredientService {
    private ProductIngredientRepository productIngredientRepository;

    public ProductIngredientService(ProductIngredientRepository productIngredientRepository) {
        this.productIngredientRepository = productIngredientRepository;
    }
}
