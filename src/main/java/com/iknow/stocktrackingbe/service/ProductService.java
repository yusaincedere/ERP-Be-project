package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.BadRequest;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.helper.StockCodeHelper;
import com.iknow.stocktrackingbe.model.*;
import com.iknow.stocktrackingbe.model.mapper.ProductResponseMapper;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.ProductRequest;
import com.iknow.stocktrackingbe.payload.request.StockCardRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.ProductRequestMapper;
import com.iknow.stocktrackingbe.payload.request.mapper.StockCardRequestMapper;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.payload.response.ProductIngredientResponseProduct;
import com.iknow.stocktrackingbe.payload.response.ProductResponse;
import com.iknow.stocktrackingbe.payload.response.StockCardResponseProduct;
import com.iknow.stocktrackingbe.repository.ProductRepository;
import com.iknow.stocktrackingbe.repository.StockCardRepository;
import com.iknow.stocktrackingbe.repository.WareHouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository productRepository;
    private final ProductIngredientService productIngredientService;

    private final ProductRequestMapper productRequestMapper;

    private final StockCardRequestMapper stockCardRequestMapper;

    private final StockCardRepository stockCardRepository;




    private final WareHouseRepository wareHouseRepository;

    public List<Product> getProducts(Pageable page) {
        logger.info("Service Called: getPageableProducts");
        Page<Product> productsPage = productRepository.findAll(page);
        if(!productsPage.getContent().isEmpty()){
           return productsPage.getContent();
        }else {
            throw new NotFoundException("There is no Product");
        }
    }
    public List<Product> getProductsByIdList(ArrayList<String> ids) {
        logger.info("Service Called: getProducts");
        List<Product> products = productRepository.findAllById(ids);
        if(!products.isEmpty()){
            return products;
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
            throw new NotFoundException("There is not product with this name");
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
        if(productRepository.existsByProductName(productRequest.getProductName())){
            logger.warn("Product"+productRequest.getProductName()+ " already exists" );
            throw new BadRequest("This product already exists");
        }else{
            List<ProductIngredient> productIngredients =new ArrayList<>();
            Product product = productRequestMapper.mapToModel(productRequest);
            for(String id:productRequest.getIngredientIds()){
                ProductIngredient productIngredient = productIngredientService.getProductIngredientById(id);
                productIngredients.add(productIngredient);
                productIngredient.getProducts().add(product);
            }
            product.setProductIngredients(productIngredients);

            productRepository.save(product);
        }
    }

    public void deleteProducts(Set<String> ids){
        logger.info("Service Called: deleteProducts");
        productRepository.deleteByIdIn(ids);
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
            productIngredientService.addProductToIngredient(productIngredient,product);
        }
        productRepository.flush();
    }
    public StockCard addStockCard(String id, StockCardRequest stockCardRequest, String wareHouseId) {
        logger.info("Service Called: addStockCard");
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("There is no product with this id")
        );
        WareHouse wareHouse = wareHouseRepository.findById(wareHouseId).orElseThrow(
                ()-> new IllegalStateException("There is no ware house with this id")
        );
        if(wareHouseRepository.existsWareHouseByProductsIsContaining(product)){
            throw new IllegalStateException("This product already exists in this warehouse");
        }
        stockCardRequest.setStockCode(checkStockCardNumber(stockCardRequest.getStockCode()));
        StockCard stockCard = stockCardRequestMapper.mapToModel(stockCardRequest,product,wareHouse);
        product.getStockCards().add(stockCard);
        wareHouse.getProducts().add(product);
        productRepository.flush();
        return stockCard;
    }

    private String checkStockCardNumber(String stockCardNumber){
        if(stockCardNumber==null||stockCardNumber.equals("")){
            boolean unique = false;
            while(!unique){
                stockCardNumber = StockCodeHelper.generateRandomCode(7,0,90);
                if(!stockCardRepository.existsByStockCode(stockCardNumber)){
                    unique =true;
                }
            }
        }else if(stockCardRepository.existsByStockCode(stockCardNumber)){
            throw new IllegalStateException("This stock card number already exists");
        }
        return stockCardNumber;
    }
}
