package com.iknow.stocktrackingbe.repository.prescription;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,String> {

    @Transactional
    void deleteByIdIn(ArrayList<String> ids);

}
