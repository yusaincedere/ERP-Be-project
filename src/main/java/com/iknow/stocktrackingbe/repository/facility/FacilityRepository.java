package com.iknow.stocktrackingbe.repository.facility;

import com.iknow.stocktrackingbe.model.facility.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Long> {

}
