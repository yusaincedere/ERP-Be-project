package com.iknow.stocktrackingbe.repository.prescription;

import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionProductRepository extends JpaRepository<PrescriptionProduct,Long> {
}
