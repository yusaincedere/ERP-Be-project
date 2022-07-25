package com.iknow.stocktrackingbe.service.prescription;

import com.iknow.stocktrackingbe.repository.prescription.PrescriptionProductRepository;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionProductService {
    private final PrescriptionProductRepository prescriptionProductRepository;

    public PrescriptionProductService(PrescriptionProductRepository prescriptionProductRepository) {
        this.prescriptionProductRepository = prescriptionProductRepository;
    }
}
