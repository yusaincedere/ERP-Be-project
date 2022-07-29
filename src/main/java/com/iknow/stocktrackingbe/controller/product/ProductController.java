package com.iknow.stocktrackingbe.controller.product;

import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getProducts(Pageable page){
        return  productService.getPageableProducts(page);
    }
    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable(required = false) String id){
        Product product = productService.getProductById(id);
        System.out.println(product);
        return product;
    }

    @PostMapping
    public void createNewProduct(@Valid @RequestBody Product product){
        System.out.println(product);
        productService.createNewProduct(product);
    }
    @PutMapping("/{id}/update")
    public void updateProduct(
            @PathVariable String id,
            @RequestBody Product product){
        System.out.println(product);
        productService.updateProduct(id,product);
    }
    @DeleteMapping(path = "/delete")
    public void deleteProducts(@RequestParam("ids") List<String> ids) {
        productService.deleteProducts(ids);
    }
}
