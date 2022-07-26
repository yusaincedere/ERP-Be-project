package com.iknow.stocktrackingbe.controller.facility;

import com.iknow.stocktrackingbe.service.facility.FacilityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/facility")
public class FacilityController {
   private final FacilityService facilityService;
   public FacilityController(FacilityService facilityService) {
      this.facilityService = facilityService;
   }
}
