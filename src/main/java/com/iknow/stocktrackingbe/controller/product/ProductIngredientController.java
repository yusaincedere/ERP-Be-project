package com.iknow.stocktrackingbe.controller.product;



import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.product.ProductIngredient;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;
import com.iknow.stocktrackingbe.service.product.ProductIngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @GetMapping
    public Page<ProductIngredient> getProductIngredients(Pageable page){
        return productIngredientService.getProductIngredients(page);
    }
    @GetMapping(path = "/{id}")
    public ProductIngredient getProductIngredientById(@PathVariable(required = false) String id){
        ProductIngredient productIngredient = productIngredientService.getProductIngredientById(id);
        return productIngredient;
    }
    @GetMapping(path = "/{id}/prescriptions")
    public List<Prescription> getPrescriptionsByProductIngredientId(@PathVariable(required = false) String id){
        return productIngredientService.getPrescriptionsByProductIngredientId(id);
    }


    @PostMapping
    public void createProductIngredient(@Valid @RequestBody ProductIngredient productIngredient){
        productIngredientService.createNewProductIngredient(productIngredient);
    }

    @PutMapping("/{id}/update")
    public void updateProductIngredient(
            @Valid
            @PathVariable String id,
            @RequestBody ProductIngredient productIngredient){
        productIngredientService.updateProductIngredient(id,productIngredient);
    }

    @DeleteMapping(path = "/delete")
    public void deleteProductIngredients(@RequestBody DeleteRequest ids) {
        productIngredientService.deleteProductIngredients(ids.getIds());
    }
}
