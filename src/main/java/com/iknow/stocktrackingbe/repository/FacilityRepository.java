package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface FacilityRepository extends JpaRepository<Facility,String> {

    Page<Facility> findAllByNameContainingIgnoreCase(String name, Pageable pageable);


}
