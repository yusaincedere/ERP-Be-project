package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.*;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.ProductRequest;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.payload.response.ProductIngredientResponseProduct;
import com.iknow.stocktrackingbe.payload.response.ProductResponse;
import com.iknow.stocktrackingbe.payload.response.StockCardResponseProduct;
import com.iknow.stocktrackingbe.repository.ProductRepository;
import com.iknow.stocktrackingbe.repository.WareHouseRepository;
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
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository productRepository;
    private final ProductIngredientService productIngredientService;


    private final WareHouseRepository wareHouseRepository;

    public Page<ProductResponse> getPageableProducts(Pageable page) {
        logger.info("Service Called: getPageableProducts");
        Page<Product> productsPage = productRepository.findAll(page);
        if(!productsPage.getContent().isEmpty()){
            int totalElements =  productsPage.getNumberOfElements();
            return new PageImpl<ProductResponse>(productsPage.getContent()
                    .stream().map(product -> ProductResponse.builder()
                            .productName(product.getProductName())
                            .amountOfUsage(product.getAmountOfUsage())
                            .productCode(product.getProductCode())
                            .expiryDate(product.getExpiryDate())
                            .prospectusId(product.getProspectus().getId())
                            .id(product.getId())
                            .produceDate(product.getProduceDate())
                            .productIngredients(
                                    product.getProductIngredients().stream().map(
                                            productIngredient -> ProductIngredientResponseProduct.builder()
                                                    .milliGramWeight(productIngredient.getMilliGramWeight())
                                                    .name(productIngredient.getName())
                                                    .id(productIngredient.getId())
                                                    .build()
                                    ).collect(Collectors.toList())
                            )
                            .stockCards(product.getStockCards().stream().map(
                                    stockCard -> StockCardResponseProduct.builder()
                                            .stockCode(stockCard.getStockCode())
                                            .stockCount(stockCard.getStockCount())
                                            .id(stockCard.getId())
                                            .build()
                            ).collect(Collectors.toList()))
                            .build()).collect(Collectors.toList()),page,totalElements);
        }else {
            throw new NotFoundException("There is no Product");
        }
    }
    public List<Product> getProducts(ArrayList<String> ids) {
        logger.info("Service Called: getProducts");
        List<Product> products = productRepository.findAllById(ids);
        if(!products.isEmpty()){
            return products;
        }else {
            throw new NotFoundException("There is no product");
        }
    }

    public ProductResponse getProductById(String id) {
        logger.info("Service Called: getProductById");
        Optional<Product> optional =  productRepository.findById(id);
        if(optional.isPresent()){
            Product product = optional.get();
            return ProductResponse.builder()
                    .productCode(product.getProductCode())
                    .productName(product.getProductName())
                    .produceDate(product.getProduceDate())
                    .expiryDate(product.getExpiryDate())
                    .amountOfUsage(product.getAmountOfUsage())
                    .id(product.getId())
                    .prospectusId(product.getProspectus().getId())
                    .stockCards(product.getStockCards().stream().map(
                            stockCard -> StockCardResponseProduct.builder()
                                    .id(stockCard.getId())
                                    .stockCode(stockCard.getStockCode())
                                    .stockCount(stockCard.getStockCount()).build()
                    ).collect(Collectors.toList()))
                    .productIngredients(product.getProductIngredients().stream().map(
                            productIngredient -> ProductIngredientResponseProduct.builder()
                                    .milliGramWeight(productIngredient.getMilliGramWeight())
                                    .name(productIngredient.getName())
                                    .id(productIngredient.getId())
                                    .build()
                    ).collect(Collectors.toList()))
                    .build();
        }else {
            logger.warn("Prescription does not exist");
            throw new NotFoundException("Prescription does not exist");
        }

    }
    public void updateProduct(String id, Product product) {
        logger.info("Service Called: updateProduct");
        Product oldProduct= productRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("There is no product with this id")
        );
        oldProduct.setProductName(product.getProductName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCurrencyType(product.getCurrencyType());
        oldProduct.setAmountOfUsage(product.getAmountOfUsage());
        oldProduct.setExpiryDate(product.getExpiryDate());
        productRepository.flush();
    }
    public void createNewProduct(ProductRequest productRequest) {
        logger.info("Service Called: createNewProduct");
        List<ProductIngredient> productIngredients = new ArrayList<>();
        Product product = new Product().toBuilder()
                .amountOfUsage(productRequest.getAmountOfUsage())
                .productName(productRequest.getProductName())
                .productCode(productRequest.getProductCode())
                .price(productRequest.getPrice())
                .expiryDate(productRequest.getExpiryDate())
                .produceDate(productRequest.getProduceDate())
                .prospectus(productRequest.getProspectus())
                .build();
        for(int i = 0; i<productRequest.getIngredientIds().size();i++){
           ProductIngredient productIngredient = productIngredientService.getProductIngredientById(productRequest.getIngredientIds().get(i));
           productIngredients.add(productIngredient);
           productIngredient.getProducts().add(product);
        }
        product.setProductIngredients(productIngredients);

        productRepository.save(product);
    }

    public void deleteProducts(List<String> ids){
        logger.info("Service Called: deleteProducts");
        productRepository.deleteByIdIn(new ArrayList<>(ids));
        logger.info("Products deleted");
    }

    public void addProductIngredients(String id, IdListRequest idListRequest) {
        logger.info("Service Called: addProductIngredients");
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("There is no product with this id")
        );
        List<ProductIngredient> productIngredients = productIngredientService.getProductIngredientsByIdList(idListRequest.getIdList());
        product.setProductIngredients(productIngredients);

        for(ProductIngredient productIngredient:productIngredients){
            productIngredientService.addProdutToIngredient(productIngredient,product);
        }
        productRepository.flush();
    }
    public void addStockCard(String id, StockCard stockCard,String wareHouseId) {
        logger.info("Service Called: addStockCard");
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("There is no product with this id")
        );
        WareHouse wareHouse = wareHouseRepository.findById(wareHouseId).orElseThrow(
                ()-> new IllegalStateException("There is no ware house with this id")
        );
        stockCard.setWareHouse(wareHouse);
        stockCard.setProduct(product);
        product.getStockCards().add(stockCard);
        wareHouse.getProducts().add(product);
        productRepository.flush();
    }
}
