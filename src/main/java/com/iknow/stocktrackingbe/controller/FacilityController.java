package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.Facility;

import com.iknow.stocktrackingbe.payload.request.FacilityRequest;
import com.iknow.stocktrackingbe.payload.request.FacilityUpdateRequest;
import com.iknow.stocktrackingbe.service.FacilityService;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/facility")
public class FacilityController {
   private final FacilityService facilityService;

   public FacilityController(FacilityService facilityService) {
      this.facilityService = facilityService;
   }

   @PostMapping
   public void createFacility(@Valid @RequestBody FacilityRequest facilityRequest) {
      facilityService.createNewFacility(facilityRequest);
   }

   @GetMapping(path = "/{id}")
   public Facility getFacilityById(@PathVariable(required = false) String id) {
      return facilityService.getFacilityById(id);
   }

   @GetMapping
   public List<Facility> getFacilities() {
      List<Facility> facilities = facilityService.getFacilities();
      return facilities;
   }

   @PutMapping("/{id}/update")
   public void updateFacility(
           @Valid
           @PathVariable String id,
           @RequestBody FacilityUpdateRequest facilityUpdateRequest) {
      facilityService.updateFacility(id, facilityUpdateRequest);
   }
}

