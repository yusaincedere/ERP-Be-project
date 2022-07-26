package com.iknow.stocktrackingbe.controller.prescription;

import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.service.prescription.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public void createPrescription(@RequestBody Prescription prescription){
        prescriptionService.createNewPrescription(prescription);
    }
    @GetMapping(path = "/{id}")
    public Prescription getPrescriptionById(@PathVariable(required = false) String id){
        Prescription pres = prescriptionService.getPrescriptionById(id);
        System.out.println(pres);
        return pres;
    }

    @PutMapping("/{id}/approve")
    public void approvePrescription(@PathVariable String id){
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
            @PathVariable String id,
            @RequestBody Prescription prescription){
        System.out.println(prescription);
        prescriptionService.updatePrescription(id,prescription);
    }

    @PostMapping("/draft")
    public void createDraftPrescription(@RequestBody Prescription prescription){
        prescriptionService.createDraftPrescription(prescription);
    }
    @GetMapping
    public List<Prescription> getPrescriptions(){
        List<Prescription> prescriptions = prescriptionService.getPrescriptions();
        return prescriptions;
    }
}