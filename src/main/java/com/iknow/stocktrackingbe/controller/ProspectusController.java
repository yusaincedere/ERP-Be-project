package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.service.ProspectusService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/prospectus")
public class ProspectusController {
    private final ProspectusService prospectusService;

    public ProspectusController(ProspectusService prospectusService) {
        this.prospectusService = prospectusService;
    }
}
