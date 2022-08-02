package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.repository.ProspectusRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProspectusService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProspectusRepository prospectusRepository;


}
