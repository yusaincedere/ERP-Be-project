package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.Facility;

import com.iknow.stocktrackingbe.payload.request.FacilityRequest;
import com.iknow.stocktrackingbe.payload.request.FacilityUpdateRequest;
import com.iknow.stocktrackingbe.service.FacilityService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/facility")
@RequiredArgsConstructor
public class FacilityController {
   private final FacilityService facilityService;





   @GetMapping(path = "/id/{id}")
   public ResponseEntity<Facility> getFacilityById(@PathVariable(required = false) String id) {
      return ResponseEntity.ok(facilityService.getFacilityById(id));
   }
   @GetMapping(path = "/name/{name}")
   public ResponseEntity<List<Facility>> searchFacilitiesByName(@PathVariable(required = false) String name, Pageable pageable) {
      return ResponseEntity.ok(facilityService.searchFacilitiesByName(name,pageable));
   }

   @GetMapping("/facilities")
   public ResponseEntity<List<Facility>> getFacilities(Pageable pageable) {
      List<Facility> facilities = facilityService.getFacilities(pageable);
      return ResponseEntity.ok(facilities);
   }
   @PostMapping
   public void createFacility(@Valid @RequestBody FacilityRequest facilityRequest) {
      facilityService.createNewFacility(facilityRequest);
   }
   @PutMapping("/{id}/update")
   public void updateFacility(
           @Valid
           @PathVariable String id,
           @RequestBody FacilityUpdateRequest facilityUpdateRequest) {
      facilityService.updateFacility(id, facilityUpdateRequest);
   }
}

