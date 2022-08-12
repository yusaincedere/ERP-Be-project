package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.ProductType;
import com.iknow.stocktrackingbe.model.ProductUnit;
import com.iknow.stocktrackingbe.payload.request.ProductUnitRequest;
import com.iknow.stocktrackingbe.repository.ProductUnitRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductUnitService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductUnitRepository productUnitRepository;

    public List<ProductUnit> getProductUnits() {
        logger.info("Service Called: getProductUnits");
        return productUnitRepository.findAll();
    }

    public ProductUnit getProductUnitByUnitName(String unit) {
        logger.info("Service Called: getProductUnitByUnitName");
        Optional<ProductUnit> optional = productUnitRepository.findByUnitName(unit);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new NotFoundException("There is no unit with this name");
        }
    }

    public void saveUnit(ProductUnitRequest productUnitRequest) {
        logger.info("Service Called: saveUnit");
        if(productUnitRepository.existsByUnitName(productUnitRequest.getUnitName())){
            throw new IllegalStateException("There is already unit with this name");
        }else{
            ProductUnit productUnit = new ProductUnit().toBuilder().unitName(productUnitRequest.getUnitName()).build();
            productUnitRepository.save(productUnit);
        }
    }

    public void updateUnit(String id, ProductUnitRequest productUnitRequest) {
        logger.info("Service Called: updateUnit");
        Optional<ProductUnit> optional = productUnitRepository.findById(id);
        if(optional.isPresent()){
            ProductUnit productUnit = optional.get();
            productUnit.setUnitName(productUnitRequest.getUnitName());
            productUnitRepository.flush();
        }
    }
    public void deleteProductUnits(Set<String> idList) {
        logger.info("Service Called: deleteProductUnits");
        productUnitRepository.deleteByIdIn(idList);
    }
}
