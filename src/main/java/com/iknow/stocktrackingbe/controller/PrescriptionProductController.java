package com.iknow.stocktrackingbe.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.iknow.stocktrackingbe.helper.JsonHelper;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;
import com.iknow.stocktrackingbe.service.PrescriptionProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/prescriptionProduct")
public class PrescriptionProductController {
    private final PrescriptionProductService prescriptionProductService;
    private final JsonHelper jsonHelper;

    @GetMapping
    public ResponseEntity<JsonNode> getPrescriptionProducts(Pageable page){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(prescriptionProductService.getPrescriptionProducts(page)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<JsonNode> getPrescriptionProductById(@PathVariable(required = false) String id){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(prescriptionProductService.getPrescriptionProductById(id)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public void createPrescriptionProduct(@Valid @RequestBody PrescriptionProduct prescriptionProduct){
        prescriptionProductService.createPrescriptionProduct(prescriptionProduct);
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
