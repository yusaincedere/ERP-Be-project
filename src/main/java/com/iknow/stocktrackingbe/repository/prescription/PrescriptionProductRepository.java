package com.iknow.stocktrackingbe.repository.prescription;

import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface PrescriptionProductRepository extends JpaRepository<PrescriptionProduct,String> {
    @Transactional
    void deleteByIdIn(ArrayList<String> ids);
}
