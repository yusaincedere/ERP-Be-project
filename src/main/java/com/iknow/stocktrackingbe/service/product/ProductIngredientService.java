package com.iknow.stocktrackingbe.service.product;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.product.ProductIngredient;
import com.iknow.stocktrackingbe.repository.product.ProductIngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductIngredientService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ProductIngredientRepository productIngredientRepository;

    public ProductIngredientService(ProductIngredientRepository productIngredientRepository) {
        this.productIngredientRepository = productIngredientRepository;
    }
    public Page<ProductIngredient> getProductIngredients(Pageable page) {
        logger.info("Service Called: getProductIngredients");

        Page<ProductIngredient> productIngredients = productIngredientRepository.findAll(page);
        if(!productIngredients.isEmpty()){
            return productIngredients;
        }else {
            throw new NotFoundException("There is no Ingredient");
        }
    }
    public ProductIngredient getProductIngredientById(String id) {
        logger.info("Service Called: getProductIngredientById");
        Optional<ProductIngredient> optional =  productIngredientRepository.findById(id) ;
        if(optional.isPresent()){
            return optional.get();
        }else {
            logger.warn("Ingredient does not exist");
            throw new NotFoundException("Ingredient does not exist");
        }
    }

    public void createNewProductIngredient(ProductIngredient productIngredient) {
        logger.info("Service Called: createNewProductIngredient");
        productIngredientRepository.save(productIngredient);
    }



    public void updateProductIngredient(String id, ProductIngredient productIngredient) {
        logger.info("Service Called: updateProductIngredient");
        ProductIngredient oldProductIngredient = getProductIngredientById(id);
        oldProductIngredient.setProducts(productIngredient.getProducts());
        oldProductIngredient.setStockCount(productIngredient.getStockCount());
        oldProductIngredient.setName(productIngredient.getName());
        oldProductIngredient.setMilliGramWeight(productIngredient.getMilliGramWeight());
        productIngredientRepository.flush();
    }



    public void deleteProductIngredients(ArrayList<String> ids) {
        logger.info("Service Called: deleteProductIngredients");
        productIngredientRepository.deleteByIdIn(ids);
        logger.info("Ingredients deleted");
    }
}
