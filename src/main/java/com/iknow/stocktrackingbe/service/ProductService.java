package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Stock;
import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.model.product.ProductType;
import com.iknow.stocktrackingbe.payload.request.product.ProductRequest;
import com.iknow.stocktrackingbe.payload.request.product.ProductUpdateRequest;
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

    private final ProductRequestMapper productRequestMapper;

    private final BomService bomService;


    public List<Product> getProducts(Pageable page) {
        logger.info("Service Called: getPageableProducts");
        Page<Product> productsPage = productRepository.findAll(page);
        if(!productsPage.getContent().isEmpty()){
           return productsPage.getContent();
        }else {
            logger.error("There is no product");
            throw new NotFoundException("There is no product");
        }
    }

    public Product getProductById(Long id) {
        logger.info("Service Called: getProductById");
        Optional<Product> optional =  productRepository.findById(id);
        if(optional.isPresent()){
            return  optional.get();
        }else {
            logger.error("Prescription does not exist");
            throw new NotFoundException("Prescription does not exist");
        }
    }
    public List<Product> searchByProductName(String name, Pageable pageable) {
        logger.info("Service Called: searchByProductName");
        Page<Product> productPage = productRepository.findAllByProductNameContainingIgnoreCase(name,pageable);
        if(!productPage.getContent().isEmpty()){
            return productPage.getContent();
        }else{
            logger.error("There is no product with this name");
            throw new NotFoundException("There is no product with this name");
        }
    }

    public List<Stock> getProductStocks(Long id) {
        logger.info("Service Called: getProductStocks");
        Product product = getProductById(id);
        return product.getStocks();
    }

    public List<Bom> findBomListByProductID(Long id) {
        logger.info("Service Called: findBomListByProductID");
        Product product = getProductById(id);
        return bomService.findBomListByProduct(product);
    }
    public void updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {
        logger.info("Service Called: updateProduct");
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isPresent()){
            Product product = optional.get();
            product.setProductType(productUpdateRequest.getProductType()==null ? optional.get().getProductType():productUpdateRequest.getProductType());
            product.setProductUnit(productUpdateRequest.getProductUnit()==null ? optional.get().getProductUnit():productUpdateRequest.getProductUnit());
            product.setProductName(productUpdateRequest.getProductName()==null ? optional.get().getProductName():productUpdateRequest.getProductName());
            product.setProductCode(productUpdateRequest.getProductCode()==null ? optional.get().getProductCode():productUpdateRequest.getProductCode());
            product.setCost(productUpdateRequest.getCost()==null ? optional.get().getCost():productUpdateRequest.getCost());
            product.setDescription(productUpdateRequest.getDescription()==null ? optional.get().getDescription():productUpdateRequest.getDescription());
            product.setDimensionType(productUpdateRequest.getDimensionType()==null ? optional.get().getDimensionType():productUpdateRequest.getDimensionType());
            product.setSelPrice(productUpdateRequest.getSelPrice()==null ? optional.get().getSelPrice():productUpdateRequest.getSelPrice());
            product.setWeightType(productUpdateRequest.getWeightType()==null ? optional.get().getWeightType():productUpdateRequest.getWeightType());
            product.setWeight(productUpdateRequest.getWeight()==null ? optional.get().getWeight():productUpdateRequest.getWeight());
            product.setLength(productUpdateRequest.getLength()==null ? optional.get().getLength():productUpdateRequest.getLength());
            product.setWidth(productUpdateRequest.getWidth()==null ? optional.get().getWidth():productUpdateRequest.getWidth());
            product.setHeight(productUpdateRequest.getHeight()==null ? optional.get().getHeight():productUpdateRequest.getHeight());
            product.setUrl(productUpdateRequest.getUrl()==null ? optional.get().getUrl():productUpdateRequest.getUrl());
            product.setToBuy(productUpdateRequest.getToBuy()==null ? optional.get().getToBuy():productUpdateRequest.getToBuy());
            product.setToSell(productUpdateRequest.getToSell()==null ? optional.get().getToSell():productUpdateRequest.getToSell());
            productRepository.flush();
        }else{
            logger.error("There is no product with this id");
            throw new NotFoundException("There is no product with this id");
        }
    }
    public Product createNewProduct(ProductRequest productRequest) {
        logger.info("Service Called: createNewProduct");
        if(productRepository.existsByProductName(productRequest.getProductName())){
            logger.error("A product with this name already exists");
            throw new IllegalStateException("A product with this name already exists");
        }else{
            Product product = productRequestMapper.mapToModel(productRequest);
            productRepository.save(product);
            logger.info("Product created");
            return product;

        }
    }
    public void deleteProducts(Set<Long> ids){
        logger.info("Service Called: deleteProducts");
        productRepository.deleteByIdIn(ids);
        logger.info("Products deleted");
    }
}
