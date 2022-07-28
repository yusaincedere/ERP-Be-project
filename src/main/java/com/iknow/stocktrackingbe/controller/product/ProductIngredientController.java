package com.iknow.stocktrackingbe.controller.product;



import com.iknow.stocktrackingbe.model.product.ProductIngredient;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;
import com.iknow.stocktrackingbe.service.product.ProductIngredientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/productIngredient")
public class ProductIngredientController {
    private final ProductIngredientService productIngredientService;

    public ProductIngredientController(ProductIngredientService productIngredientService) {
        this.productIngredientService = productIngredientService;
    }

    @PostMapping
    public void createProductIngredient(@Valid @RequestBody ProductIngredient productIngredient){
        productIngredientService.createNewProductIngredient(productIngredient);
    }
    @GetMapping(path = "/{id}")
    public ProductIngredient getProductIngredientById(@PathVariable(required = false) String id){
        ProductIngredient productIngredient = productIngredientService.getProductIngredientById(id);
        return productIngredient;
    }
    @PutMapping("/{id}/update")
    public void updateProductIngredient(
            @Valid
            @PathVariable String id,
            @RequestBody ProductIngredient productIngredient){
        productIngredientService.updateProductIngredient(id,productIngredient);
    }
    @GetMapping
    public List<ProductIngredient> getProductIngredients(){
        List<ProductIngredient> productIngredients = productIngredientService.getProductIngredients();
        return productIngredients;
    }
    @DeleteMapping(path = "/delete")
    public void deleteProductIngredients(@RequestBody DeleteRequest ids) {
        productIngredientService.deleteProductIngredients(ids.getIds());
    }
}
