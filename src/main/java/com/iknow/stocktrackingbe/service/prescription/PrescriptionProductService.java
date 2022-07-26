package com.iknow.stocktrackingbe.service.prescription;

import com.iknow.stocktrackingbe.repository.prescription.PrescriptionProductRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PrescriptionProductService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final PrescriptionProductRepository prescriptionProductRepository;

    public PrescriptionProductService(PrescriptionProductRepository prescriptionProductRepository) {
        this.prescriptionProductRepository = prescriptionProductRepository;
    }
}
