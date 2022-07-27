package com.iknow.stocktrackingbe.service.product;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.repository.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public void createNewProduct(Product product) {
        productRepository.save(product);
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

    public List<Product> getProducts() {
        logger.info("Service Called: getProducts");
        return productRepository.findAll();
    }

    public void deleteProducts(List<String> ids){
        logger.info("Service Called: deleteProducts");
        productRepository.deleteByIdIn(new ArrayList<>(ids));
        logger.info("Products deleted");
    }
}
