package com.iknow.stocktrackingbe.controller.prescription;

import com.iknow.stocktrackingbe.service.prescription.PrescriptionProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/prescriptionProduct")
public class PrescriptionProductController {
    private final PrescriptionProductService prescriptionProductService;

    public PrescriptionProductController(PrescriptionProductService prescriptionProductService) {
        this.prescriptionProductService = prescriptionProductService;
    }
}
