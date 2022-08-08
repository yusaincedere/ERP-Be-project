package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.Product;

import com.iknow.stocktrackingbe.payload.response.PrescriptionProductResponse;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.repository.PrescriptionProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PrescriptionProductRepository prescriptionProductRepository;

    public List<PrescriptionProduct> getPrescriptionProducts(Pageable page) {
        logger.info("Service Called: getPrescriptionProducts");

        Page<PrescriptionProduct> prescriptionProductPage = prescriptionProductRepository.findAll(page);
        if(!prescriptionProductPage.isEmpty()){
            return  prescriptionProductPage.getContent();
        }else {
            throw new NotFoundException("There is no Prescription product");
        }
    }
    public PrescriptionProduct getPrescriptionProductById(String id) {
        logger.info("Service Called: getPrescriptionProductById");
        Optional<PrescriptionProduct> optional =  prescriptionProductRepository.findById(id) ;
        if(optional.isPresent()){
            return optional.get();
        }else {
            logger.warn("Prescription product does not exist");
            throw new NotFoundException("Prescription product does not exist");
        }
    }
    public List<PrescriptionProduct> getPrescriptionProductsByProducts(List<Product> products) {
        return prescriptionProductRepository.findAllByProductIn(products);
    }

    public void updatePrescriptionProduct(String id, PrescriptionProduct prescriptionProduct) {
        logger.info("Service Called: updatePrescriptionProduct");
        Optional<PrescriptionProduct> optional = prescriptionProductRepository.findById(id);
        if(optional.isPresent()){
            PrescriptionProduct oldPrescriptionProduct = optional.get();
            oldPrescriptionProduct.setProduct(prescriptionProduct.getProduct());
            oldPrescriptionProduct.setEndDate(prescriptionProduct.getEndDate());
            oldPrescriptionProduct.setStartDate(prescriptionProduct.getStartDate());
            oldPrescriptionProduct.setQuantity(prescriptionProduct.getQuantity());
            oldPrescriptionProduct.setUsageDescriptions(prescriptionProduct.getUsageDescriptions());
            prescriptionProductRepository.flush();
        }else{
            throw new IllegalStateException("Error while updating");
        }

    }
    public void deletePrescriptionProducts(List<String> ids) {
            logger.info("Service Called: deletePrescriptionProducts");
            prescriptionProductRepository.deleteByIdIn(ids);
            logger.info("Ingredients deleted");
    }
}
