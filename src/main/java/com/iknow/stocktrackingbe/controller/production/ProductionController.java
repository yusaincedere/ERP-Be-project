package com.iknow.stocktrackingbe.controller.production;

import com.iknow.stocktrackingbe.model.production.Production;
import com.iknow.stocktrackingbe.service.production.ProductionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path="/production")

public class ProductionController {
    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }
    @GetMapping(path="/{id}")
    public Production getProductionById(@PathVariable(required = false)String id){
        Production production = productionService.getProductionById(id);
        System.out.println(production);
        return production;
    }
    @GetMapping()
    public List<Production> getProductions(@PathVariable(required = false)String id){
        List<Production>productions =productionService.getProductions();
        return productions;

    }
    @PostMapping()
    public void createNewProduction(@Valid @RequestBody Production production){
        productionService.createNewProduction(production);
    }
}

