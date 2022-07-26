package com.iknow.stocktrackingbe.service.product;

import com.iknow.stocktrackingbe.repository.product.ProductIngredientRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProductIngredientService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private ProductIngredientRepository productIngredientRepository;

    public ProductIngredientService(ProductIngredientRepository productIngredientRepository) {
        this.productIngredientRepository = productIngredientRepository;
    }
}
