package com.iknow.stocktrackingbe.controller.prescription;


import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;
import com.iknow.stocktrackingbe.service.prescription.PrescriptionProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/prescriptionProduct")
public class PrescriptionProductController {
    private final PrescriptionProductService prescriptionProductService;

    public PrescriptionProductController(PrescriptionProductService prescriptionProductService) {
        this.prescriptionProductService = prescriptionProductService;
    }

    @PostMapping
    public void createPrescriptionProduct(@Valid @RequestBody PrescriptionProduct prescriptionProduct){
        prescriptionProductService.createPrescriptionProduct(prescriptionProduct);
    }
    @GetMapping(path = "/{id}")
    public PrescriptionProduct getPrescriptionProductById(@PathVariable(required = false) String id){
        PrescriptionProduct prescriptionProduct = prescriptionProductService.getPrescriptionProductById(id);
        return prescriptionProduct;
    }
    @PutMapping("/{id}/update")
    public void updatePrescriptionProduct(
            @Valid
            @PathVariable String id,
            @RequestBody PrescriptionProduct prescriptionProduct){
        prescriptionProductService.updatePrescriptionProduct(id,prescriptionProduct);
    }
    @GetMapping
    public List<PrescriptionProduct> getPrescriptionProducts(){
        List<PrescriptionProduct> prescriptionProducts = prescriptionProductService.getPrescriptionProducts();
        return prescriptionProducts;
    }
    @DeleteMapping(path = "/delete")
    public void deletePrescriptionProducts(@RequestBody DeleteRequest ids) {
        prescriptionProductService.deletePrescriptionProducts(ids.getIds());
    }
}
