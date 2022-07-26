package com.iknow.stocktrackingbe.service.product;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public void createNewProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(String id) {
            Optional<Product> optional =  productRepository.findById(id) ;
            if(optional.isPresent()){
                Product product = optional.get();
                return product;
            }else {
                throw new IllegalStateException("there is no product");
            }

    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
