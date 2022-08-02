package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.service.FacilityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/facility")
public class FacilityController {
   private final FacilityService facilityService;
   public FacilityController(FacilityService facilityService) {
      this.facilityService = facilityService;
   }
}
