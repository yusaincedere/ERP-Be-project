package com.iknow.stocktrackingbe.controller.facility;

import com.iknow.stocktrackingbe.model.facility.Facility;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.service.facility.FacilityService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/facility")
public class FacilityController {
   private final FacilityService facilityService;

   public FacilityController(FacilityService facilityService) {
      this.facilityService = facilityService;
   }

   @PostMapping
   public void createFacility(@Valid @RequestBody Facility facility) {
      facilityService.createNewFacility(facility);
   }

   @GetMapping(path = "{id}")
   public Facility getFacilityById(@PathVariable(required = false) String id) {
      Facility facility = facilityService.getFacilityById(id);
      return facility;
   }

   @GetMapping
   public List<Facility> getFacilitys() {
      List<Facility> facilitys = facilityService.getFacilitys;
      return facilitys;
   }

   @PutMapping("/{id}/update")
   public void updateFacility(
           @Valid
           @PathVariable String id,
           @RequestBody Facility facility) {
      System.out.println(facility);
      facilityService.updateFacility(id, facility);
   }
}

