package com.iknow.stocktrackingbe.model.mapper;

import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.payload.response.PrescriptionProductResponse;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrescriptionResponseMapper {
    public PrescriptionResponse mapper(Prescription prescription) {
        return  PrescriptionResponse.builder()
                .prescriptionProducts(prescription.getPrescriptionProducts())
                .created(prescription.getCreated())
                .prescriptionVersion(prescription.getPrescriptionVersion())
                .approved(prescription.isApproved())
                .id(prescription.getId())
                .endDate(prescription.getEndDate())
                .startDate(prescription.getStartDate())
                .draft(prescription.isDraft()).build();
    }
    public List<PrescriptionResponse> mapper(List<Prescription> prescriptions) {
        return prescriptions.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }

    public PrescriptionProductResponse prescriptionProductMapper(PrescriptionProduct prescriptionProduct){
        return PrescriptionProductResponse.builder()
                .productName(prescriptionProduct.getProductName())
                .quantity(prescriptionProduct.getQuantity())
                .startDate(prescriptionProduct.getStartDate())
                .usageDescriptions(prescriptionProduct.getUsageDescriptions())
                .endDate(prescriptionProduct.getEndDate())
                .build();
    }

    public List<PrescriptionProductResponse> prescriptionProductsMapper(List<PrescriptionProduct> prescriptionProducts){
        return prescriptionProducts.stream()
                .map(this::prescriptionProductMapper)
                .collect(Collectors.toList());
    }
}

