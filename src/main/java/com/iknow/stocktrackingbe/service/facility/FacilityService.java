package com.iknow.stocktrackingbe.service.facility;

import com.iknow.stocktrackingbe.repository.facility.FacilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class FacilityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }
}
