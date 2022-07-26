package com.iknow.stocktrackingbe.service.prospectus;

import com.iknow.stocktrackingbe.repository.prospectus.ProspectusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProspectusService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProspectusRepository prospectusRepository;

    public ProspectusService(ProspectusRepository prospectusRepository) {
        this.prospectusRepository = prospectusRepository;
    }
}
