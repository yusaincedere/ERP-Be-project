package com.iknow.stocktrackingbe.repository;
import com.iknow.stocktrackingbe.model.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public interface PrescriptionRepository extends JpaRepository<Prescription,String> {

    void deleteByIdIn(List<String> ids);

    Page<Prescription> findAllByPrescriptionVersionContainingIgnoreCase(String version, Pageable pageable);



}
