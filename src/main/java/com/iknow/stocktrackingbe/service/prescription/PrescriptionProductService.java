package com.iknow.stocktrackingbe.service.prescription;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.product.Product;

import com.iknow.stocktrackingbe.model.product.ProductIngredient;
import com.iknow.stocktrackingbe.repository.prescription.PrescriptionProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PrescriptionProductRepository prescriptionProductRepository;

    public PrescriptionProductService(PrescriptionProductRepository prescriptionProductRepository) {
        this.prescriptionProductRepository = prescriptionProductRepository;
    }

    public void createPrescriptionProduct(PrescriptionProduct prescriptionProduct) {
        logger.info("Service Called: createPrescriptionProduct");
        prescriptionProductRepository.save(prescriptionProduct);
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

    public List<PrescriptionProduct> getPrescriptionProducts() {
        logger.info("Service Called: getPrescriptionProducts");

        List<PrescriptionProduct> productIngredients = prescriptionProductRepository.findAll();
        if(!productIngredients.isEmpty()){
            return productIngredients;
        }else {
            throw new NotFoundException("There is no Prescription product");
        }
    }

    public void deletePrescriptionProducts(ArrayList<String> ids) {
            logger.info("Service Called: deletePrescriptionProducts");
            prescriptionProductRepository.deleteByIdIn(ids);
            logger.info("Ingredients deleted");

    }
}
