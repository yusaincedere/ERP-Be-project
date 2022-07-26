package com.iknow.stocktrackingbe.service.prescription;

import com.iknow.stocktrackingbe.repository.prescription.PrescriptionProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PrescriptionProductRepository prescriptionProductRepository;

    public PrescriptionProductService(PrescriptionProductRepository prescriptionProductRepository) {
        this.prescriptionProductRepository = prescriptionProductRepository;
    }
}
