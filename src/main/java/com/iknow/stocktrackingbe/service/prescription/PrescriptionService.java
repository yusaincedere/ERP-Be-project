package com.iknow.stocktrackingbe.service.prescription;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import com.iknow.stocktrackingbe.repository.prescription.PrescriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PrescriptionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public void createNewPrescription(Prescription prescription){
        logger.info("Service Called: createNewPrescription");
        prescriptionRepository.save(prescription);
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

    public void approvePrescription(String id) {
        logger.info("Service Called: approvePrescription");
        Prescription prescription = getPrescriptionById(id);
        prescription.setApproved(true);
        prescriptionRepository.flush();
    }

    public Prescription clonePrescription(String id) {
        logger.info("Service Called: clonePrescription");
        Prescription prescription = getPrescriptionById(id);
        Set<PrescriptionProduct> products =  prescription.getPrescriptionProducts();
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

    public List<Prescription> getPrescriptions() {
        logger.info("Service Called: getPrescriptions");

        List<Prescription> prescriptions = prescriptionRepository.findAll();
        if(!prescriptions.isEmpty()){
            return prescriptions;
        }else {
            throw new NotFoundException("There is no Prescription");
        }
    }
    public void deletePrescriptions(List<String> ids) {
        logger.info("Service Called: deletePrescriptions");
        prescriptionRepository.deleteByIdIn(new ArrayList<>(ids));
        logger.info("Prescriptions deleted");
    }
}
