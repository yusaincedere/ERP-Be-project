package com.iknow.stocktrackingbe.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.ProductIngredient;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;
import com.iknow.stocktrackingbe.payload.request.ProductIngredientRequest;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.service.ProductIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/productIngredient")
@RequiredArgsConstructor
public class ProductIngredientController {
    private final ProductIngredientService productIngredientService;
    @GetMapping
    public Page<ProductIngredient> getProductIngredients(Pageable page){
            return productIngredientService.getProductIngredients(page);
    }
    @GetMapping(path = "/{id}")
    public ProductIngredient getProductIngredientById(@PathVariable(required = false) String id){
            return productIngredientService.getProductIngredientById(id);
    }
    @GetMapping(path = "/{id}/prescriptions")
    public Page<PrescriptionResponse> getPrescriptionsByProductIngredientId(@PathVariable(required = false) String id, Pageable page){
            return productIngredientService.getPrescriptionsByProductIngredientId(id,page);
    }


    @PostMapping
    public void createProductIngredient(@Valid @RequestBody ProductIngredientRequest productIngredientRequest){
        productIngredientService.createNewProductIngredient(productIngredientRequest);
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
