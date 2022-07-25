package com.iknow.stocktrackingbe.service.product;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public void createNewProduct(Product product) {
        productRepository.save(product);
    }
}
