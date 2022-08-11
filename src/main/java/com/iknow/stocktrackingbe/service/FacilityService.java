package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Facility;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.payload.request.FacilityRequest;
import com.iknow.stocktrackingbe.payload.request.FacilityUpdateRequest;
import com.iknow.stocktrackingbe.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FacilityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FacilityRepository facilityRepository;
    private final WareHouseService wareHouseService;




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
    public List<Facility> searchFacilitiesByName(String name, Pageable pageable) {
        logger.info("Servis Called: CreateNewFacility");
        Page<Facility> facilityPage =facilityRepository.findAllByNameContainingIgnoreCase(name,pageable);
        if(!facilityPage.getContent().isEmpty()){
            return facilityPage.getContent();
        }else {
            throw new NotFoundException("There is no facility with this name");
        }
    }


    public List<Facility> getFacilities(Pageable pageable){
        logger.info("Service called: getFacilities" );
        Page<Facility> facilityPage =facilityRepository.findAll(pageable);
        if(!facilityPage.getContent().isEmpty()){
           return facilityPage.getContent();
        }else{
            throw new NotFoundException("There is no Facility");
        }
    }
    public void createNewFacility(FacilityRequest facilityRequest) {
        logger.info("Servis Called: CreateNewFacility");
        Facility facility = new Facility().toBuilder()
                .address(facilityRequest.getAddress()).name(facilityRequest.getName()).build();
        facilityRepository.saveAndFlush(facility);
    }

    public void updateFacility(String id, FacilityUpdateRequest facilityUpdateRequest) {
        logger.info("Service Called: updateFacility");
        Facility facility = getFacilityById(id);
        facility.setName(facilityUpdateRequest.getName());
        facility.setAddress(facilityUpdateRequest.getAddress());
        List<WareHouse> wareHouses = wareHouseService.getWareHosesByIds(facilityUpdateRequest.getWareHouseIds());
        facility.setWareHouses(wareHouses);
        facilityRepository.flush();
    }

}
