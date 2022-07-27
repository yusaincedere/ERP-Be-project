package com.iknow.stocktrackingbe.service.production;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.production.Production;
import com.iknow.stocktrackingbe.repository.production.ProductionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductionRepository productionRepository;

    public ProductionService(ProductionRepository productionRepository){
        this.productionRepository=productionRepository;
    }

    public void createNewProduction(Production production) {productionRepository.save(production);}

    public Production getProductionById(String id) {
        logger.info("Service Called: getProductionById");
        Optional<Production>optional = productionRepository.findById(id);
            if(optional.isPresent()){
                return optional.get();
            }else {
                logger.warn("Production not found");
                throw new NotFoundException("Production not found");
            }
    }

    public List<Production> getProductions() {
        logger.info("Service Called: getProductions");
        List<Production> productions = productionRepository.findAll();
        if(!productions.isEmpty()){
            return productions;
        }else {
            throw new NotFoundException("There is no Production");
        }

    }
}
