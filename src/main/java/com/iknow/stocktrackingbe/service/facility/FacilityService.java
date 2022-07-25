package com.iknow.stocktrackingbe.service.facility;

import com.iknow.stocktrackingbe.repository.facility.FacilityRepository;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {
    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }
}
