package com.iknow.stocktrackingbe.controller.product;


import com.iknow.stocktrackingbe.service.product.ProductIngredientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/productIngredient")
public class ProductIngredientController {
    private final ProductIngredientService productIngredientService;

    public ProductIngredientController(ProductIngredientService productIngredientService) {
        this.productIngredientService = productIngredientService;
    }
}
