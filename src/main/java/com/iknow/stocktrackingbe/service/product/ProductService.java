package com.iknow.stocktrackingbe.service.product;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.repository.product.ProductRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
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
                Product product = optional.get();
                return product;
            }else {
                logger.warning("Product not found");
                throw new IllegalStateException("Product not found");
            }

    }

    public List<Product> getProducts() {
        logger.info("Service Called: getProducts");
        return productRepository.findAll();
    }
}
