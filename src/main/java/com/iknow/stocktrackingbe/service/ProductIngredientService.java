package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.model.ProductIngredient;
import com.iknow.stocktrackingbe.payload.request.ProductIngredientRequest;
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
    private final PrescriptionProductService prescriptionProductService;



    public Page<ProductIngredient> getProductIngredients(Pageable page) {
        logger.info("Service Called: getProductIngredients");

        Page<ProductIngredient> productIngredients = productIngredientRepository.findAll(page);
        if(!productIngredients.isEmpty()){
            return productIngredients;
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
    public Page<PrescriptionResponse> getPrescriptionsByProductIngredientId(String id,Pageable page) {

        logger.info("Service Called: getPrescriptionsByProductIngredient");
        List<Product> products = getProductIngredientById(id).getProducts();
        List<PrescriptionProduct> prescriptionProducts =prescriptionProductService.getPrescriptionProducstByProducts(products);
        List<Prescription> prescriptions = new ArrayList<>();


        for(PrescriptionProduct prescriptionProduct:prescriptionProducts){
            prescriptions.add(prescriptionProduct.getPrescription());
        }
        return new PageImpl<>(prescriptions.stream().map(
                prescription ->
                PrescriptionResponse.builder()
                        .startDate(prescription.getStartDate())
                        .endDate(prescription.getEndDate())
                        .draft(prescription.isDraft())
                        .prescriptionVersion(prescription.getPrescriptionVersion())
                        .id(prescription.getId())
                        .approved(prescription.isApproved())
                        .created(prescription.getCreated())
                        .prescriptionProducts(prescription.getPrescriptionProducts())
                        .build()
        ).collect(Collectors.toList()),page,prescriptions.size());
    }


    public List<ProductIngredient> getProductIngredientsByIdList(List<String> idList){
        logger.info("Service Called: getProductIngredientsByIdList");
        return productIngredientRepository.findAllById(idList);
    }


    public void createNewProductIngredient(ProductIngredientRequest productIngredientRequest) {
        logger.info("Service Called: createNewProductIngredient");
        List<Product> productList = productRepository.findAllById(productIngredientRequest.getProductIds());
        ProductIngredient productIngredient = new ProductIngredient().toBuilder()
                .milliGramWeight(productIngredientRequest.getMilliGramWeight())
                .stockCount(productIngredientRequest.getStockCount())
                .name(productIngredientRequest.getName())
                .products(productList)
                .build();
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



    public void deleteProductIngredients(ArrayList<String> ids) {
        logger.info("Service Called: deleteProductIngredients");
        productIngredientRepository.deleteByIdIn(ids);
        logger.info("Ingredients deleted");
    }
}
