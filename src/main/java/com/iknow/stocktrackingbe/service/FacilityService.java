package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class FacilityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FacilityRepository facilityRepository;
}
