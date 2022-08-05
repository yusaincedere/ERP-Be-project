package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;
import com.iknow.stocktrackingbe.payload.response.PrescriptionProductResponse;

import com.iknow.stocktrackingbe.service.PrescriptionProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/prescriptionProduct")
public class PrescriptionProductController {
    private final PrescriptionProductService prescriptionProductService;

    @GetMapping
    public Page<PrescriptionProductResponse> getPrescriptionProducts(Pageable page){
        return prescriptionProductService.getPrescriptionProducts(page);
    }
    @GetMapping(path = "/{id}")
    public PrescriptionProductResponse getPrescriptionProductById(@PathVariable(required = false) String id){
            return prescriptionProductService.getPrescriptionProductById(id);
    }


    @PutMapping("/{id}/update")
    public void updatePrescriptionProduct(
            @Valid
            @PathVariable String id,
            @RequestBody PrescriptionProduct prescriptionProduct){
        prescriptionProductService.updatePrescriptionProduct(id,prescriptionProduct);
    }

    @DeleteMapping(path = "/delete")
    public void deletePrescriptionProducts(@RequestBody DeleteRequest ids) {
        prescriptionProductService.deletePrescriptionProducts(ids.getIds());
    }
}
