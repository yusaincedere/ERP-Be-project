package com.iknow.stocktrackingbe.service.prescription;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import com.iknow.stocktrackingbe.repository.prescription.PrescriptionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PrescriptionService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public void createNewPrescription(Prescription prescription){
        prescriptionRepository.save(prescription);
    }

    public Prescription getPrescriptionById(String id) {
        logger.info("Service Called: getPrescriptionById");
        Optional<Prescription> optional =  prescriptionRepository.findById(id) ;
        if(optional.isPresent()){
            Prescription prescription = optional.get();
            return prescription;
        }else {
            logger.warning("Prescription not found");
            throw new IllegalStateException("Prescription not found");
        }
    }

    public void approvePrescription(String id) {
        logger.info("Service Called: approvePrescription");
        Prescription prescription = prescriptionRepository.findById(id).orElseThrow(()->
           new IllegalStateException("there is no prescription")
        );
        prescription.setApproved(true);
        prescriptionRepository.flush();
    }

    public Prescription clonePrescription(String id) {
        logger.info("Service Called: clonePrescription");
        Prescription prescription = getPrescriptionById(id);
        List<PrescriptionProduct> products =  prescription.getPrescriptionProducts();
        Prescription clonePrescription = Prescription.builder().prescriptionProducts(products).
                startDate(prescription.getStartDate()).endDate(prescription.getEndDate()).build();
        return clonePrescription;
    }

    public void updatePrescription(String id, Prescription newPrescription){
        logger.info("Service Called: updatePrescription");
        Prescription oldPrescription = getPrescriptionById(id);

        if(oldPrescription.getStartDate() != newPrescription.getStartDate()){
            oldPrescription.setStartDate(newPrescription.getStartDate());
        }
        if(oldPrescription.getEndDate()!= newPrescription.getEndDate()){
            oldPrescription.setEndDate(newPrescription.getEndDate());
        }
        if(oldPrescription.getPrescriptionProducts()!= newPrescription.getPrescriptionProducts()){
            oldPrescription.setPrescriptionProducts(newPrescription.getPrescriptionProducts());
        }
        if(oldPrescription.getPrescriptionVersion()!=newPrescription.getPrescriptionVersion()){
            oldPrescription.setPrescriptionVersion(newPrescription.getPrescriptionVersion());
        }
        prescriptionRepository.flush();
    }

    public void createDraftPrescription(Prescription prescription) {
        logger.info("Service Called: createDraftPrescription");
        prescription.setDraft(true);
        createNewPrescription(prescription);
    }

    public List<Prescription> getPrescriptions() {
        logger.info("Service Called: getPrescriptions");
        return prescriptionRepository.findAll();
    }
}
