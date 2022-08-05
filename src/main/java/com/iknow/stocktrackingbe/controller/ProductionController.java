package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.Production;
import com.iknow.stocktrackingbe.payload.request.ProductionRequest;
import com.iknow.stocktrackingbe.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping(path="/api/production")
@RequiredArgsConstructor
public class ProductionController {
    private final ProductionService productionService;



    @GetMapping(path="/{id}")
    public Production getProductionById(@PathVariable(required = false)String id){
            return productionService.getProductionById(id);
    }
    @GetMapping()
    public Page<Production> getProductions(Pageable page){
            return productionService.getProductions(page);

    }
    @PostMapping()
    public void createNewProduction(@Valid @RequestBody ProductionRequest productionRequest){
        productionService.createNewProduction(productionRequest);
    }

    @PutMapping("/{id}/update")
    public void updateProduction(
            @Valid
            @PathVariable String id,
            @RequestBody Production production){
        productionService.updateProduction(id,production);
    }
    @PutMapping("/{id}/complete")
    public void completeProduction(
            @Valid
            @PathVariable String id){
        productionService.completeProduction(id);
    }
    @PutMapping("/{id}/cancel")
    public void cancelProduction(
            @Valid
            @PathVariable String id){
        productionService.cancelProduction(id);
    }
}

