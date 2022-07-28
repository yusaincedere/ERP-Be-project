package com.iknow.stocktrackingbe.controller.production;

import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.model.production.Production;
import com.iknow.stocktrackingbe.model.production.ProductionStatus;
import com.iknow.stocktrackingbe.payload.request.ProductionRequest;
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
        return production;
    }
    @GetMapping()
    public List<Production> getProductions(@PathVariable(required = false)String id){
        List<Production>productions =productionService.getProductions();
        return productions;

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

