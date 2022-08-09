package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.model.ProductIngredient;
import com.iknow.stocktrackingbe.payload.request.ProductIngredientRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.IngredientRequestMapper;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.repository.ProductIngredientRepository;
import com.iknow.stocktrackingbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductIngredientService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductIngredientRepository productIngredientRepository;

    private final ProductRepository productRepository;
    private final IngredientRequestMapper ingredientRequestMapper;
    private final PrescriptionProductService prescriptionProductService;



    public List<ProductIngredient> getProductIngredients(Pageable page) {
        logger.info("Service Called: getProductIngredients");
        Page<ProductIngredient> productIngredientsPage = productIngredientRepository.findAll(page);
        if(!productIngredientsPage.getContent().isEmpty()){
            return productIngredientsPage.getContent();
        }else {
            throw new NotFoundException("There is no Ingredient");
        }
    }
    public ProductIngredient getProductIngredientById(String id) {
        logger.info("Service Called: getProductIngredientById");
        Optional<ProductIngredient> optional =  productIngredientRepository.findById(id) ;
        if(optional.isPresent()){
            return optional.get();
        }else {
            logger.warn("Ingredient does not exist");
            throw new NotFoundException("Ingredient does not exist");
        }
    }
    public List<Prescription> getPrescriptionsByProductIngredientId(String id) {
        logger.info("Service Called: getPrescriptionsByProductIngredient");
        List<Product> products = getProductIngredientById(id).getProducts();
        List<PrescriptionProduct> prescriptionProducts =prescriptionProductService.getPrescriptionProductsByProducts(products);
        List<Prescription> prescriptions = new ArrayList<>();
        for(PrescriptionProduct prescriptionProduct:prescriptionProducts){
            prescriptions.add(prescriptionProduct.getPrescription());
        }
        return prescriptions;
    }


    public List<ProductIngredient> getProductIngredientsByIdList(List<String> idList){
        logger.info("Service Called: getProductIngredientsByIdList");
        return productIngredientRepository.findAllById(idList);
    }

    public List<ProductIngredient> searchIngredientsByName(String name, Pageable pageable) {
        logger.info("Service Called: searchIngredientsByName");
        Page<ProductIngredient> productIngredientPage = productIngredientRepository.findAllByNameContainingIgnoreCase(name,pageable);
        if(!productIngredientPage.getContent().isEmpty()){
            return productIngredientPage.getContent();
        }else{
            throw new NotFoundException("There is no ingredient with this name");
        }
    }


    public void createNewProductIngredient(ProductIngredientRequest productIngredientRequest) {
        logger.info("Service Called: createNewProductIngredient");
        List<Product> productList = productRepository.findAllById(productIngredientRequest.getProductIds());
        ProductIngredient productIngredient = ingredientRequestMapper.mapToModel(productIngredientRequest,productList);
        for(Product product:productList){
            product.getProductIngredients().add(productIngredient);
        }
        productRepository.flush();
        productIngredientRepository.save(productIngredient);
    }


    public void addProdutToIngredient(ProductIngredient productIngredient,Product product){
        List<Product> products = productIngredient.getProducts();
        products.add(product);
        productIngredient.setProducts(products);
    }


    public void updateProductIngredient(String id, ProductIngredient productIngredient) {
        logger.info("Service Called: updateProductIngredient");
        ProductIngredient oldProductIngredient = getProductIngredientById(id);
        oldProductIngredient.setProducts(productIngredient.getProducts());
        oldProductIngredient.setStockCount(productIngredient.getStockCount());
        oldProductIngredient.setName(productIngredient.getName());
        oldProductIngredient.setMilliGramWeight(productIngredient.getMilliGramWeight());
        productIngredientRepository.flush();
    }



    public void deleteProductIngredients(List<String> ids) {
        logger.info("Service Called: deleteProductIngredients");
        productIngredientRepository.deleteByIdIn(ids);
        logger.info("Ingredients deleted");
    }


}
