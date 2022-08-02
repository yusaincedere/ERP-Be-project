package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.model.ProductIngredient;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository productRepository;
    private final ProductIngredientService productIngredientService;




    public Page<Product> getPageableProducts(Pageable page) {
        logger.info("Service Called: getPageableProducts");
        Page<Product> products = productRepository.findAll(page);
        if(!products.isEmpty()){
            return products;
        }else {
            throw new NotFoundException("There is no product");
        }
    }
    public List<Product> getProducts(ArrayList<String> ids) {
        logger.info("Service Called: getProducts");
        List<Product> products = productRepository.findAllById(ids);
        if(!products.isEmpty()){
            return products;
        }else {
            throw new NotFoundException("There is no product");
        }
    }

    public Product getProductById(String id) {
        logger.info("Service Called: getProductById");
        Optional<Product> optional =  productRepository.findById(id) ;
        if(optional.isPresent()){
            return optional.get();
        }else {
            logger.warn("Product not found");
            throw new IllegalStateException("Product not found");
        }

    }
    public void updateProduct(String id, Product product) {
        logger.info("Service Called: updateProduct");
        Product oldProduct= getProductById(id);
        oldProduct.setProductName(product.getProductName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCurrencyType(product.getCurrencyType());
        oldProduct.setAmountOfUsage(product.getAmountOfUsage());
        oldProduct.setExpiryDate(product.getExpiryDate());
        productRepository.flush();
    }
    public void createNewProduct(Product product) {
        logger.info("Service Called: createNewProduct");
        productRepository.save(product);
    }

    public void deleteProducts(List<String> ids){
        logger.info("Service Called: deleteProducts");
        productRepository.deleteByIdIn(new ArrayList<>(ids));
        logger.info("Products deleted");
    }

    public void addProductIngredients(String id, IdListRequest idListRequest) {
        logger.info("Service Called: addProductIngredients");
        Product product = getProductById(id);
        List<ProductIngredient> productIngredients = productIngredientService.getProductIngredientsByIdList(idListRequest.getIdList());
        product.setProductIngredients(productIngredients);

        for(ProductIngredient productIngredient:productIngredients){
            productIngredientService.addProdutToIngredient(productIngredient,product);
        }
        productRepository.flush();
    }
}
