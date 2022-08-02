package com.iknow.stocktrackingbe.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.iknow.stocktrackingbe.helper.JsonHelper;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;

import com.iknow.stocktrackingbe.payload.request.PrescriptionProductRequest;
import com.iknow.stocktrackingbe.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/prpescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final JsonHelper jsonHelper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<JsonNode> getPrescriptionById(@PathVariable(required = false) String id){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(prescriptionService.getPrescriptionById(id)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping
    public ResponseEntity<JsonNode> getPrescriptions(@PageableDefault (sort = "created",direction = Sort.Direction.ASC) Pageable page){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(prescriptionService.getPrescriptions(page)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping(path = "/{id}/clone")
    public ResponseEntity<JsonNode> clonePrescription(@PathVariable String id){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(prescriptionService.clonePrescription(id)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
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