package com.iknow.stocktrackingbe.service.prospectus;

import com.iknow.stocktrackingbe.repository.prospectus.ProspectusRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProspectusService{
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final ProspectusRepository prospectusRepository;

    public ProspectusService(ProspectusRepository prospectusRepository) {
        this.prospectusRepository = prospectusRepository;
    }
}
