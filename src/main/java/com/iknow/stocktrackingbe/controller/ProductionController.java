package com.iknow.stocktrackingbe.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.iknow.stocktrackingbe.helper.JsonHelper;
import com.iknow.stocktrackingbe.model.Production;
import com.iknow.stocktrackingbe.payload.request.ProductionRequest;
import com.iknow.stocktrackingbe.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path="/api/production")
@RequiredArgsConstructor
public class ProductionController {
    private final ProductionService productionService;
    private final JsonHelper jsonHelper;


    @GetMapping(path="/{id}")
    public ResponseEntity<JsonNode> getProductionById(@PathVariable(required = false)String id){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(productionService.getProductionById(id)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping()
    public ResponseEntity<JsonNode> getProductions(Pageable page){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(productionService.getProductions(page)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
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

