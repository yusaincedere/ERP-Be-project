package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.*;
import com.iknow.stocktrackingbe.payload.request.mapper.PrescriptionRequestMapper;
import com.iknow.stocktrackingbe.payload.request.PrescriptionProductListRequest;
import com.iknow.stocktrackingbe.payload.request.PrescriptionProductRequest;
import com.iknow.stocktrackingbe.payload.request.PrescriptionRequest;
import com.iknow.stocktrackingbe.repository.PrescriptionRepository;
import com.iknow.stocktrackingbe.repository.ProductRepository;
import com.iknow.stocktrackingbe.repository.WareHouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PrescriptionRepository prescriptionRepository;
    private final ProductRepository productRepository;
    private final WareHouseRepository wareHouseRepository;
    private final PrescriptionRequestMapper prescriptionRequestMapper;


    public List<Prescription> getPrescriptions(Pageable page) {
        logger.info("Service Called: getPrescriptions");
        Page<Prescription> prescriptionsPage = prescriptionRepository.findAll(page);
        if(!prescriptionsPage.getContent().isEmpty()){
            return prescriptionsPage.getContent();
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
    public List<Prescription> searchByVersion(String version,Pageable pageable) {
        logger.info("Service Called: getPrescriptionById");
        Page<Prescription> prescriptionsPage = prescriptionRepository.findAllByPrescriptionVersionContainingIgnoreCase(version,pageable);
        if(!prescriptionsPage.getContent().isEmpty()){
            return prescriptionsPage.getContent();
        }else{
            throw new NotFoundException("There is no prescription with this version number");
        }
    }
    public void createNewPrescription(PrescriptionRequest prescriptionRequest){
        logger.info("Service Called: createNewPrescription");
        WareHouse wareHouse = wareHouseRepository.findById(prescriptionRequest.getWareHouseId()).orElseThrow(
                ()-> new IllegalStateException("There is no ware house with this id")
        );
        Prescription prescription = prescriptionRequestMapper.mapToModel(prescriptionRequest);
        prescription.setWareHouse(wareHouse);
        List<PrescriptionProductRequest> prescriptionProductRequests = prescriptionRequest.getPrescriptionProductRequests();
        for(PrescriptionProductRequest prescriptionProductRequest:prescriptionProductRequests){
            Product product = productRepository.findById(prescriptionProductRequest.getProductId()).orElseThrow(
                    ()-> new IllegalStateException("There is no product with this id")
            );
            PrescriptionProduct prescriptionProduct = prescriptionRequestMapper.productMapper(prescriptionProductRequest,product,prescription);
            prescription.getPrescriptionProducts().add(prescriptionProduct);
        }
        prescriptionRepository.save(prescription);
    }



    public void approvePrescription(String id) {
        logger.info("Service Called: approvePrescription");
        Optional<Prescription> optional = prescriptionRepository.findById(id);
        if(optional.isPresent()){
            Prescription prescription = optional.get();
            prescription.setApproved(true);
            List <PrescriptionProduct> products = prescription.getPrescriptionProducts();
            for(PrescriptionProduct prescriptionProduct:products){
                List<StockCard> stockCards = prescriptionProduct.getProduct().getStockCards();
                for(StockCard stockCard:stockCards){
                    if(stockCard.getWareHouse() == prescription.getWareHouse()){
                        stockCard.setStockCount(stockCard.getStockCount()-1);
                    }
                }
            }
            prescriptionRepository.flush();
        }else{
            throw new IllegalStateException("Error while approving");
        }

    }

    public Prescription clonePrescription(String id) {
        logger.info("Service Called: clonePrescription");
        Optional<Prescription> optional = prescriptionRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new IllegalStateException("Error while cloning");
        }
    }

    public void updatePrescription(String id, Prescription prescription){
        logger.info("Service Called: updatePrescription");
        Optional<Prescription> optional = prescriptionRepository.findById(id);
        if(optional.isPresent()){
            Prescription oldPrescription = optional.get();
            System.out.println(prescription);
            oldPrescription.setPrescriptionVersion(prescription.getPrescriptionVersion());
            oldPrescription.setPrescriptionProducts(prescription.getPrescriptionProducts());
            oldPrescription.setEndDate(prescription.getEndDate());
            oldPrescription.setStartDate(prescription.getStartDate());
            oldPrescription.setApproved(prescription.isApproved());
            oldPrescription.setDraft(prescription.isDraft());
            oldPrescription.setWareHouse(prescription.getWareHouse());
            prescriptionRepository.flush();
        }else {
            throw new IllegalStateException("Error while updating");
        }

    }

    public void createDraftPrescription(PrescriptionRequest prescriptionRequest) {
        logger.info("Service Called: createDraftPrescription");
        WareHouse wareHouse = wareHouseRepository.findById(prescriptionRequest.getWareHouseId()).orElseThrow(
                ()-> new IllegalStateException("There is no ware house with this id")
        );
        Prescription prescription = prescriptionRequestMapper.mapToModel(prescriptionRequest);
        prescription.setWareHouse(wareHouse);
        List<PrescriptionProductRequest> prescriptionProductRequests = prescriptionRequest.getPrescriptionProductRequests();
        for(PrescriptionProductRequest prescriptionProductRequest:prescriptionProductRequests){
            Product product = productRepository.findById(prescriptionProductRequest.getProductId()).orElseThrow(
                    ()-> new IllegalStateException("There is no product with this id")
            );
            PrescriptionProduct prescriptionProduct =prescriptionRequestMapper.productMapper(prescriptionProductRequest,product,prescription);
            prescription.getPrescriptionProducts().add(prescriptionProduct);
        }
        prescription.setDraft(true);
        prescriptionRepository.save(prescription);
    }

    public void deletePrescriptions(Set<String> ids) {
        logger.info("Service Called: deletePrescriptions");
        prescriptionRepository.deleteByIdIn(ids);
        logger.info("Prescriptions deleted");
    }

    public void addProductsToPrescription(String id, PrescriptionProductListRequest prescriptionProductListRequest) {
        logger.info("Service Called: addProductsToPrescription");
        Optional<Prescription> optional = prescriptionRepository.findById(id);
        if(optional.isPresent()){
            Prescription prescription = optional.get();
            List<PrescriptionProductRequest> prescriptionProductRequests = prescriptionProductListRequest.getPrescriptionProductRequests();
            for(PrescriptionProductRequest prescriptionProductRequest:prescriptionProductRequests){
                Product product = productRepository.findById(prescriptionProductRequest.getProductId()).orElseThrow(
                        ()-> new IllegalStateException("There is no product with this id")
                );
                PrescriptionProduct prescriptionProduct = prescriptionRequestMapper.productMapper(prescriptionProductRequest,product,prescription);
                prescription.getPrescriptionProducts().add(prescriptionProduct);

            }
            prescriptionRepository.flush();
        }else {
            throw new IllegalStateException("Error while adding products");
        }
    }



}
