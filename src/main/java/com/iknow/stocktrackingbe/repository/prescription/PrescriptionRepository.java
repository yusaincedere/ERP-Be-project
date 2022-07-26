package com.iknow.stocktrackingbe.repository.prescription;

import com.iknow.stocktrackingbe.model.prescription.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,String> {
}
