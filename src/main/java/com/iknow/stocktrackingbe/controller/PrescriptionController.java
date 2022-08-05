package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.payload.request.DeleteRequest;

import com.iknow.stocktrackingbe.payload.request.PrescriptionProductListRequest;
import com.iknow.stocktrackingbe.payload.request.PrescriptionRequest;
import com.iknow.stocktrackingbe.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;


    @GetMapping(path = "/{id}")
    public PrescriptionResponse getPrescriptionById(@PathVariable(required = false) String id){
          return  prescriptionService.getPrescriptionById(id);
    }
    @GetMapping
    public Page<PrescriptionResponse> getPrescriptions(@PageableDefault (sort = "created",direction = Sort.Direction.ASC) Pageable page){
        return  prescriptionService.getPrescriptions(page);
    }

    @GetMapping(path = "/{id}/clone")
    public PrescriptionResponse clonePrescription(@PathVariable String id){
            return prescriptionService.clonePrescription(id);
    }

    @PostMapping
    public void createPrescription(@Valid @RequestBody PrescriptionRequest prescriptionRequest){
        prescriptionService.createNewPrescription(prescriptionRequest);
    }
    @PostMapping("/draft")
    public void createDraftPrescription(@Valid  @RequestBody PrescriptionRequest prescriptionRequest){
        prescriptionService.createDraftPrescription(prescriptionRequest);
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
            @RequestBody PrescriptionProductListRequest prescriptionProductListRequest){
        prescriptionService.addProductsToPrescription(id,prescriptionProductListRequest);
    }
    @DeleteMapping(path = "/delete")
    public void deletePrescriptions(@RequestBody DeleteRequest ids) {
        prescriptionService.deletePrescriptions(ids.getIds());
    }
}