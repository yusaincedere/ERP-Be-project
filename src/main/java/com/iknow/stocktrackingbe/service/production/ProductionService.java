package com.iknow.stocktrackingbe.service.production;

import com.iknow.stocktrackingbe.exception.BadRequest;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.prescription.Prescription;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.model.product.ProductIngredient;
import com.iknow.stocktrackingbe.model.production.Production;
import com.iknow.stocktrackingbe.model.production.ProductionStatus;
import com.iknow.stocktrackingbe.model.stock.StockCard;
import com.iknow.stocktrackingbe.payload.request.ProductionRequest;
import com.iknow.stocktrackingbe.repository.production.ProductionRepository;
import com.iknow.stocktrackingbe.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class ProductionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductionRepository productionRepository;
    ProductService productService;

    public ProductionService(ProductionRepository productionRepository,ProductService productService){
        this.productionRepository=productionRepository;
        this.productService = productService;
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
    public Production getProductionById(String id) {
        logger.info("Service Called: getProductionById");
        Optional<Production>optional = productionRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new NotFoundException("Production not found");
        }
    }

    public void createNewProduction(ProductionRequest productionRequest) {
        logger.info("Service Called: createNewProduction");
        Product product = productService.getProductById(productionRequest.getProductId());
        Production production = Production.builder().productionCount(productionRequest.getProductionCount()).product(product).productionStatus(ProductionStatus.EMPTY).build();
        List<ProductIngredient> productIngredients = production.getProduct().getProductIngredients();
        for(ProductIngredient productIngredient:productIngredients){
            productIngredient.setStockCount(productIngredient.getStockCount()-production.getProductionCount());
        }
        productionRepository.save(production);
    }
    public void updateProduction(String id,Production production) {
        logger.info("Service Called: updateProduction");
        Production oldProduction = getProductionById(id);
        oldProduction.setProduct(production.getProduct());
        oldProduction.setProductionStatus(production.getProductionStatus());
        oldProduction.setProductionCount(production.getProductionCount());
        productionRepository.flush();
    }


    public void completeProduction(String id) {
        logger.info("Service Called: completeProduction");
        Production production = getProductionById(id);
        production.setProductionStatus(ProductionStatus.FINISHED);
        StockCard stockCard = production.getProduct().getStockCard();
        stockCard.setStockCount(stockCard.getStockCount()+production.getProductionCount());
        productionRepository.flush();
    }
    public void cancelProduction(String id) {
        logger.info("Service Called: cancelProduction");
        Production production = getProductionById(id);
        production.setProductionStatus(ProductionStatus.CANCELLED);
        List<ProductIngredient> productIngredients = production.getProduct().getProductIngredients();
        for(ProductIngredient productIngredient:productIngredients){
            productIngredient.setStockCount(productIngredient.getStockCount()+production.getProductionCount());
        }
        productionRepository.flush();
    }
}
