package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.*;
import com.iknow.stocktrackingbe.payload.request.ProductRequest;
import com.iknow.stocktrackingbe.payload.request.ProductUpdateRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.ProductRequestMapper;
import com.iknow.stocktrackingbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository productRepository;

    private final ProductUnitService productUnitService;

    private final ProductTypeService productTypeService;

    private final ProductRequestMapper productRequestMapper;



    public List<Product> getProducts(Pageable page) {
        logger.info("Service Called: getPageableProducts");
        Page<Product> productsPage = productRepository.findAll(page);
        if(!productsPage.getContent().isEmpty()){
           return productsPage.getContent();
        }else {
            throw new NotFoundException("There is no product");
        }
    }

    public Product getProductById(String id) {
        logger.info("Service Called: getProductById");
        Optional<Product> optional =  productRepository.findById(id);
        if(optional.isPresent()){
            return  optional.get();
        }else {
            logger.warn("Prescription does not exist");
            throw new NotFoundException("Prescription does not exist");
        }
    }
    public List<Product> searchByProductName(String name, Pageable pageable) {
        logger.info("Service Called: getProductById");
        Page<Product> productPage = productRepository.findAllByProductNameContainingIgnoreCase(name,pageable);
        if(!productPage.getContent().isEmpty()){
            return productPage.getContent();
        }else{
            throw new NotFoundException("There is no product with this name");
        }
    }
    public void updateProduct(String id, ProductUpdateRequest productUpdateRequest) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isPresent()){
            Product product = optional.get();
            ProductUnit unit = productUnitService.getProductUnitByUnitName(productUpdateRequest.getProductUnit());
            ProductType type = productTypeService.getProductTypeByTypeName(productUpdateRequest.getProductType());
            product.setProductType(type);
            product.setProductName(productUpdateRequest.getProductName());
            product.setProductCode(productUpdateRequest.getProductCode());
            product.setProductUnit(unit);
            productRepository.flush();
        }
    }
    public void createNewProduct(ProductRequest productRequest) {
        logger.info("Service Called: createNewProduct");
        if(productRepository.existsByProductName(productRequest.getProductName())){
            throw new IllegalStateException("A product with this name already exists");
        }else{
            ProductUnit productUnit = productUnitService.getProductUnitByUnitName(productRequest.getProductUnit());
            ProductType productType = productTypeService.getProductTypeByTypeName(productRequest.getProductType());
            Product product = productRequestMapper.mapToModel(productRequest,productUnit,productType);
            productRepository.save(product);
            logger.info("Product created");
        }
    }
    public void deleteProducts(Set<String> ids){
        logger.info("Service Called: deleteProducts");
        productRepository.deleteByIdIn(ids);
        logger.info("Products deleted");
    }

}
