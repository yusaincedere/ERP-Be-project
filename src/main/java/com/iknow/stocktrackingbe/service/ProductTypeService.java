package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.ProductType;
import com.iknow.stocktrackingbe.payload.request.ProductTypeRequest;
import com.iknow.stocktrackingbe.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductTypeRepository productTypeRepository;

    public List<ProductType> getProductTypes() {
        logger.info("Service Called: getProductTypes");
        return productTypeRepository.findAll();
    }

    public ProductType getProductTypeByTypeName(String type) {
        logger.info("Service Called: getProductTypeByTypeName");
        Optional<ProductType> optional = productTypeRepository.findByType(type);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new NotFoundException("There is no product type with this id");
        }
    }
    public void saveType(ProductTypeRequest productTypeRequest) {
        logger.info("Service Called: saveType");
        if(productTypeRepository.existsByType(productTypeRequest.getTypeName())){
            throw new IllegalStateException("This product type name already exists");
        }else{
            ProductType productType = new ProductType().toBuilder().type(productTypeRequest.getTypeName()).build();
            productTypeRepository.save(productType);
        }
    }

    public void updateType(String id, ProductTypeRequest productTypeRequest){
        logger.info("Service Called: updateType");
        Optional<ProductType> optional = productTypeRepository.findById(id);
        if(optional.isPresent()){
            ProductType productType = optional.get();
            productType.setType(productTypeRequest.getTypeName());
            productTypeRepository.flush();
        }else{
            throw new NotFoundException("There is no type with this id");
        }
    }
    public void deleteProductTypes(Set<String> idList) {
        logger.info("Service Called: deleteProductTypes");
        productTypeRepository.deleteByIdIn(idList);
    }
}
