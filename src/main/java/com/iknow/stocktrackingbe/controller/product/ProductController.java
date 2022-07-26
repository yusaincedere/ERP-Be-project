package com.iknow.stocktrackingbe.controller.product;

import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.service.product.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable(required = false) String id){
        Product product = productService.getProductById(id);
        System.out.println(product);
        return product;
    }
    @GetMapping
    public List<Product> getProducts(@PathVariable(required = false) String id){
        List<Product> products = productService.getProducts();
        return products;
    }
}