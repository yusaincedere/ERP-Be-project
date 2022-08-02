package com.iknow.stocktrackingbe.controller;



import com.fasterxml.jackson.databind.JsonNode;
import com.iknow.stocktrackingbe.helper.JsonHelper;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.ProductIngredient;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;
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
    private final JsonHelper jsonHelper;

    @GetMapping
    public ResponseEntity<JsonNode> getProductIngredients(Pageable page){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(productIngredientService.getProductIngredients(page)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<JsonNode> getProductIngredientById(@PathVariable(required = false) String id){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(productIngredientService.getProductIngredientById(id)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping(path = "/{id}/prescriptions")
    public ResponseEntity<JsonNode> getPrescriptionsByProductIngredientId(@PathVariable(required = false) String id){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(productIngredientService.getPrescriptionsByProductIngredientId(id)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
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
