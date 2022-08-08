package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.Facility;

import com.iknow.stocktrackingbe.payload.request.FacilityRequest;
import com.iknow.stocktrackingbe.payload.request.FacilityUpdateRequest;
import com.iknow.stocktrackingbe.service.FacilityService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/facility")
@RequiredArgsConstructor
public class FacilityController {
   private final FacilityService facilityService;



   @PostMapping
   public void createFacility(@Valid @RequestBody FacilityRequest facilityRequest) {
      facilityService.createNewFacility(facilityRequest);
   }

   @GetMapping(path = "/{id}")
   public ResponseEntity<Facility> getFacilityById(@PathVariable(required = false) String id) {
      return ResponseEntity.ok(facilityService.getFacilityById(id));
   }

   @GetMapping
   public ResponseEntity<List<Facility>> getFacilities() {
      List<Facility> facilities = facilityService.getFacilities();
      return ResponseEntity.ok(facilities);
   }

   @PutMapping("/{id}/update")
   public void updateFacility(
           @Valid
           @PathVariable String id,
           @RequestBody FacilityUpdateRequest facilityUpdateRequest) {
      facilityService.updateFacility(id, facilityUpdateRequest);
   }
}

