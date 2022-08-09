package com.iknow.stocktrackingbe.controller;
import com.iknow.stocktrackingbe.model.ProductIngredient;
import com.iknow.stocktrackingbe.model.mapper.PrescriptionResponseMapper;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.ProductIngredientRequest;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.service.ProductIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/productIngredient")
@RequiredArgsConstructor
public class ProductIngredientController {
    private final ProductIngredientService productIngredientService;

    private final PrescriptionResponseMapper prescriptionResponseMapper;


    @GetMapping("/productIngredients")
    public ResponseEntity<List<ProductIngredient>> getProductIngredients(Pageable pageable){
            return ResponseEntity.ok(productIngredientService.getProductIngredients(pageable));
    }
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<ProductIngredient> getProductIngredientById(@PathVariable(required = false) String id){
            return ResponseEntity.ok(productIngredientService.getProductIngredientById(id));
    }
    @GetMapping(path = "/{id}/prescriptions")
    public ResponseEntity<List<PrescriptionResponse>> getPrescriptionsByProductIngredientId(@PathVariable(required = false) String id){
            return ResponseEntity.ok(prescriptionResponseMapper.mapper(productIngredientService.getPrescriptionsByProductIngredientId(id)));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<ProductIngredient>> searchIngredientsByName(@PathVariable(required = false) String name,Pageable pageable){
        return ResponseEntity.ok(productIngredientService.searchIngredientsByName(name,pageable));
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
    public void deleteProductIngredients(@RequestBody IdListRequest idList) {
        productIngredientService.deleteProductIngredients(idList.getIdList());
    }
}
