package com.iknow.stocktrackingbe.controller.prescription;

import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.service.prescription.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public void createPrescription(@Valid @RequestBody Prescription prescription){
        prescriptionService.createNewPrescription(prescription);
    }
    @GetMapping(path = "/{id}")
    public Prescription getPrescriptionById(@PathVariable(required = false) String id){
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        return prescription;
    }

    @PutMapping("/{id}/approve")
    public void approvePrescription(@Valid @PathVariable String id){
        prescriptionService.approvePrescription(id);
    }

    @GetMapping(path = "/{id}/clone")
    public Prescription clonePrescription(@PathVariable String id){
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        Prescription clonePrescription = prescriptionService.clonePrescription(id);
        return clonePrescription;
    }
    @PutMapping("/{id}/update")
    public void updatePrescription(
            @Valid
            @PathVariable String id,
            @RequestBody Prescription prescription){
        System.out.println(prescription);
        prescriptionService.updatePrescription(id,prescription);
    }

    @PostMapping("/draft")
    public void createDraftPrescription(@Valid  @RequestBody Prescription prescription){
        prescriptionService.createDraftPrescription(prescription);
    }
    @GetMapping
    public List<Prescription> getPrescriptions(){
        List<Prescription> prescriptions = prescriptionService.getPrescriptions();
        return prescriptions;
    }
    @DeleteMapping(path = "/delete")
    public void deletePrescriptions(@RequestParam("ids") List<String> ids) {
        prescriptionService.deletePrescriptions(ids);
    }
}