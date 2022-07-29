package com.iknow.stocktrackingbe.controller.prescription;
import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;
import com.iknow.stocktrackingbe.service.prescription.PrescriptionProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping(path = "/prescriptionProduct")
public class PrescriptionProductController {
    private final PrescriptionProductService prescriptionProductService;

    public PrescriptionProductController(PrescriptionProductService prescriptionProductService) {
        this.prescriptionProductService = prescriptionProductService;
    }
    @GetMapping
    public Page<PrescriptionProduct> getPrescriptionProducts(Pageable page){
        return prescriptionProductService.getPrescriptionProducts(page);
    }
    @GetMapping(path = "/{id}")
    public PrescriptionProduct getPrescriptionProductById(@PathVariable(required = false) String id){
        PrescriptionProduct prescriptionProduct = prescriptionProductService.getPrescriptionProductById(id);
        return prescriptionProduct;
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
