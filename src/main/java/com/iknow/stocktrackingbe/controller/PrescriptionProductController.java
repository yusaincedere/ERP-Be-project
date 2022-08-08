package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.mapper.PrescriptionResponseMapper;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.response.PrescriptionProductResponse;

import com.iknow.stocktrackingbe.service.PrescriptionProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/prescriptionProduct")
public class PrescriptionProductController {
    private final PrescriptionProductService prescriptionProductService;
    private final PrescriptionResponseMapper prescriptionResponseMapper;

    @GetMapping("/prescriptionProducts")
    public ResponseEntity<List<PrescriptionProductResponse>> getPrescriptionProducts(Pageable page){
        return ResponseEntity.ok(prescriptionResponseMapper.prescriptionProductsMapper(prescriptionProductService.getPrescriptionProducts(page)));
    }
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<PrescriptionProductResponse> getPrescriptionProductById(@PathVariable(required = false) String id){
            return ResponseEntity.ok(prescriptionResponseMapper.prescriptionProductMapper(prescriptionProductService.getPrescriptionProductById(id)));
    }


    @PutMapping("/{id}/update")
    public void updatePrescriptionProduct(
            @Valid
            @PathVariable String id,
            @RequestBody PrescriptionProduct prescriptionProduct){
        prescriptionProductService.updatePrescriptionProduct(id,prescriptionProduct);
    }

    @DeleteMapping(path = "/delete")
    public void deletePrescriptionProducts(@RequestBody IdListRequest ids) {
        prescriptionProductService.deletePrescriptionProducts(ids.getIdList());
    }
}
