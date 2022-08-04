package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.Product;

import com.iknow.stocktrackingbe.repository.PrescriptionProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrescriptionProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PrescriptionProductRepository prescriptionProductRepository;

    public Page<PrescriptionProduct> getPrescriptionProducts(Pageable page) {
        logger.info("Service Called: getPrescriptionProducts");

        Page<PrescriptionProduct> productIngredients = prescriptionProductRepository.findAll(page);
        if(!productIngredients.isEmpty()){
            return productIngredients;
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
    public List<PrescriptionProduct> getPrescriptionProducstByProducts(List<Product> products) {
        return prescriptionProductRepository.findAllByProductIn(products);
    }

    public void updatePrescriptionProduct(String id, PrescriptionProduct prescriptionProduct) {
        logger.info("Service Called: updatePrescriptionProduct");
        PrescriptionProduct oldPrescriptionProduct = getPrescriptionProductById(id);
        oldPrescriptionProduct.setProduct(prescriptionProduct.getProduct());
        oldPrescriptionProduct.setEndDate(prescriptionProduct.getEndDate());
        oldPrescriptionProduct.setStartDate(prescriptionProduct.getStartDate());
        oldPrescriptionProduct.setQuantity(prescriptionProduct.getQuantity());
        oldPrescriptionProduct.setUsageDescriptions(prescriptionProduct.getUsageDescriptions());
        prescriptionProductRepository.flush();
    }
    public void deletePrescriptionProducts(ArrayList<String> ids) {
            logger.info("Service Called: deletePrescriptionProducts");
            prescriptionProductRepository.deleteByIdIn(ids);
            logger.info("Ingredients deleted");
    }
}
