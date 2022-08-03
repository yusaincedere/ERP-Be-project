package com.iknow.stocktrackingbe.service.facility;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.facility.Facility;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.repository.facility.FacilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FacilityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FacilityRepository facilityRepository;
    public List<Facility> getFacilitys;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public void createNewFacility(Facility facility) {
        logger.info("Servis Called: CreateNewFacility");
        facilityRepository.saveAndFlush(facility);
    }

    public Facility getFacilityById(String id) {
        logger.info("Service Called: getFacilityGetById");
        Optional<Facility>optional=facilityRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else{
            logger.warn("Facility does not exist");
            throw new NotFoundException("Facility does not exist");
        }
    }
    public List<Facility> getFacilitys(){
        logger.info("Service called: getfacility" );
        List<Facility>facility =facilityRepository.findAll();
        if(!facility.isEmpty()){
            return facility;
        }else{
            throw new NotFoundException("There is no Facility");
        }
    }

    public void updateFacility(String id, Facility facility) {
            logger.info("Service Called: updateFacility");
            Facility oldFacility = getFacilityById(id);
            oldFacility.setName(facility.getName());
            oldFacility.setAddress(facility.getAddress());
            oldFacility.setWareHouses(facility.getWareHouses());
            facilityRepository.flush();
    }
}
