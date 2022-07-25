package com.iknow.stocktrackingbe.controller.prospectus;

import com.iknow.stocktrackingbe.service.prospectus.ProspectusService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/prospectus")
public class ProspectusController {
    private final ProspectusService prospectusService;

    public ProspectusController(ProspectusService prospectusService) {
        this.prospectusService = prospectusService;
    }
}
