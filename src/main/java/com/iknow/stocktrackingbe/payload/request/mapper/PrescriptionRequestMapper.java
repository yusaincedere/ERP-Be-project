package com.iknow.stocktrackingbe.payload.request.mapper;

import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.payload.request.PrescriptionProductRequest;
import com.iknow.stocktrackingbe.payload.request.PrescriptionRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PrescriptionRequestMapper {
    public Prescription mapToModel(PrescriptionRequest prescriptionRequest) {
        return new Prescription().toBuilder()
                .startDate(prescriptionRequest.getStartDate())
                .endDate(prescriptionRequest.getEndDate())
                .prescriptionVersion(prescriptionRequest.getPrescriptionVersion())
                .build();
    }

    public PrescriptionProduct productMapper(PrescriptionProductRequest prescriptionProductRequest, Product product, Prescription prescription) {
        return new PrescriptionProduct().toBuilder()
                .quantity(prescriptionProductRequest.getQuantity())
                .usageDescriptions(prescriptionProductRequest.getUsageDescriptions())
                .endDate(prescriptionProductRequest.getEndDate())
                .startDate(prescriptionProductRequest.getStartDate())
                .productName(product.getProductName())
                .product(product)
                .prescription(prescription)
                .build();
    }
}
