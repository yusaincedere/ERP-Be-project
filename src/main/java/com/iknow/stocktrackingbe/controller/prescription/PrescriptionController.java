package com.iknow.stocktrackingbe.controller.prescription;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;

import com.iknow.stocktrackingbe.payload.request.PrescriptionProductRequest;
import com.iknow.stocktrackingbe.service.prescription.PrescriptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@RestController
@RequestMapping(path = "/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public Page<Prescription> getPrescriptions(@PageableDefault (sort = "created",direction = Sort.Direction.ASC) Pageable page){
        return  prescriptionService.getPrescriptions(page);

    }
    @GetMapping(path = "/{id}")
    public Prescription getPrescriptionById(@PathVariable(required = false) String id){
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        return prescription;
    }
    @GetMapping(path = "/{id}/clone")
    public Prescription clonePrescription(@PathVariable String id){
        Prescription clonePrescription = prescriptionService.clonePrescription(id);
        return clonePrescription;
    }

    @PostMapping
    public void createPrescription(@Valid @RequestBody Prescription prescription){
        prescriptionService.createNewPrescription(prescription);
    }
    @PostMapping("/draft")
    public void createDraftPrescription(@Valid  @RequestBody Prescription prescription){
        prescriptionService.createDraftPrescription(prescription);
    }

    @PutMapping("/{id}/approve")
    public void approvePrescription(@Valid @PathVariable String id){
        prescriptionService.approvePrescription(id);
    }


    @PutMapping("/{id}/update")
    public void updatePrescription(
            @Valid
            @PathVariable String id,
            @RequestBody Prescription prescription){
        prescriptionService.updatePrescription(id,prescription);
    }
    @PutMapping("/{id}/add")
    public void addProductsToPrescription(
            @Valid
            @PathVariable String id,
            @RequestBody PrescriptionProductRequest prescriptionProductRequest){
        prescriptionService.addProductsToPrescription(id,prescriptionProductRequest);
    }
    @DeleteMapping(path = "/delete")
    public void deletePrescriptions(@RequestBody DeleteRequest ids) {
        prescriptionService.deletePrescriptions(ids.getIds());
    }
}