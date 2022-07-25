package com.iknow.stocktrackingbe.controller.product;

import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.service.product.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void createNewProduct(@RequestBody Product product){
        productService.createNewProduct(product);
    }
}
