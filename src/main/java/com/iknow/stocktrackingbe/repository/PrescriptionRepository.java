package com.iknow.stocktrackingbe.repository;
import com.iknow.stocktrackingbe.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;


@Repository
@Transactional
public interface PrescriptionRepository extends JpaRepository<Prescription,String> {

    void deleteByIdIn(ArrayList<String> ids);

}
