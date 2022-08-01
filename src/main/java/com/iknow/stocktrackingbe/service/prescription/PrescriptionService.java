package com.iknow.stocktrackingbe.service.prescription;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;

import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.model.stock.StockCard;

import com.iknow.stocktrackingbe.payload.request.PrescriptionProductRequest;
import com.iknow.stocktrackingbe.repository.prescription.PrescriptionRepository;
import com.iknow.stocktrackingbe.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PrescriptionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PrescriptionRepository prescriptionRepository;
    private final ProductService productService;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, ProductService productService, ProductService productService1) {
        this.prescriptionRepository = prescriptionRepository;
        this.productService = productService1;
    }

    public Page<Prescription> getPrescriptions(Pageable page) {
        logger.info("Service Called: getPrescriptions");

        Page<Prescription> prescriptions = prescriptionRepository.findAll(page);
        if(!prescriptions.isEmpty()){
            return prescriptions;
        }else {
            throw new NotFoundException("There is no Prescription");
        }
    }
    public Prescription getPrescriptionById(String id) {
        logger.info("Service Called: getPrescriptionById");
        Optional<Prescription> optional =  prescriptionRepository.findById(id) ;
        if(optional.isPresent()){
            return optional.get();
        }else {
            logger.warn("Prescription does not exist");
            throw new NotFoundException("Prescription does not exist");
        }
    }
    public void createNewPrescription(Prescription prescription){
        logger.info("Service Called: createNewPrescription");
        prescriptionRepository.save(prescription);
    }



    public void approvePrescription(String id) {
        logger.info("Service Called: approvePrescription");
        Prescription prescription = getPrescriptionById(id);
        prescription.setApproved(true);
        List    <PrescriptionProduct> products = prescription.getPrescriptionProducts();

        for(PrescriptionProduct prescriptionProduct:products){
            StockCard stockCard = prescriptionProduct.getProduct().getStockCard();
            stockCard.setStockCount(stockCard.getStockCount()-1);
        }
        prescriptionRepository.flush();
    }

    public Prescription clonePrescription(String id) {
        logger.info("Service Called: clonePrescription");
        Prescription prescription = getPrescriptionById(id);
        List<PrescriptionProduct> products =  prescription.getPrescriptionProducts();
        return Prescription.builder().prescriptionProducts(products).
                startDate(prescription.getStartDate()).endDate(prescription.getEndDate()).build();
    }

    public void updatePrescription(String id, Prescription prescription){
        logger.info("Service Called: updatePrescription");
        Prescription oldPrescription = getPrescriptionById(id);
        oldPrescription.setPrescriptionVersion(prescription.getPrescriptionVersion());
        oldPrescription.setPrescriptionProducts(prescription.getPrescriptionProducts());
        oldPrescription.setEndDate(prescription.getEndDate());
        oldPrescription.setStartDate(prescription.getStartDate());
        prescriptionRepository.flush();
    }

    public void createDraftPrescription(Prescription prescription) {
        logger.info("Service Called: createDraftPrescription");
        prescription.setDraft(true);
        prescriptionRepository.save(prescription);
    }

    public void deletePrescriptions(ArrayList<String> ids) {
        logger.info("Service Called: deletePrescriptions");
        prescriptionRepository.deleteByIdIn(ids);
        logger.info("Prescriptions deleted");
    }

    public void addProductsToPrescription(String id, PrescriptionProductRequest prescriptionProductRequest) {
        logger.info("Service Called: addProductsToPrescription");
        Prescription prescription = getPrescriptionById(id);
        List<PrescriptionProduct> prescriptionProductList = prescriptionProductRequest.getPrescriptionProductList();
        List<String> idList = prescriptionProductRequest.getProductIdList();
        for(int i = 0;i < idList.size();i++){
            Product product = productService.getProductById(idList.get(i));
            prescriptionProductList.get(i).setProduct(product);
            prescriptionProductList.get(i).setPrescription(prescription);

        }
        prescription.setPrescriptionProducts(prescriptionProductList);
        prescriptionRepository.flush();
    }

}
