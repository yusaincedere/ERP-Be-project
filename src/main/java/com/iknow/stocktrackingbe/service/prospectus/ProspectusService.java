package com.iknow.stocktrackingbe.service.prospectus;

import com.iknow.stocktrackingbe.repository.prospectus.ProspectusRepository;
import org.springframework.stereotype.Service;

@Service
public class ProspectusService{
    private final ProspectusRepository prospectusRepository;

    public ProspectusService(ProspectusRepository prospectusRepository) {
        this.prospectusRepository = prospectusRepository;
    }
}
