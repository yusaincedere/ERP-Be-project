package com.iknow.stocktrackingbe.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.iknow.stocktrackingbe.helper.JsonHelper;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.StockCardRequest;
import com.iknow.stocktrackingbe.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/product")
public class ProductController {
    private final ProductService productService;
    private final JsonHelper jsonHelper;


    @GetMapping
    public ResponseEntity<JsonNode> getProducts(Pageable page){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(productService.getPageableProducts(page)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping(path = "/{id}")
    public  ResponseEntity<JsonNode> getProductById(@PathVariable(required = false) String id){
        try{
            return new ResponseEntity<>(jsonHelper.objectJson(productService.getProductById(id)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public void createNewProduct(@Valid @RequestBody Product product){
        System.out.println(product);
        productService.createNewProduct(product);
    }
    @PutMapping("/{id}/update")
    public void updateProduct(
            @PathVariable String id,
            @RequestBody Product product){
        System.out.println(product);
        productService.updateProduct(id,product);
    }
    @PutMapping("/{id}/addIngredients")
    public void addProductIngredients(
            @PathVariable String id,
            @RequestBody IdListRequest idListRequest){
        productService.addProductIngredients(id,idListRequest);
    }
    @PutMapping("/{id}/addStockCard")
    public void addStockCard(
            @PathVariable String id,
            @RequestBody StockCardRequest stockCardRequest){
        productService.addStockCard(id,stockCardRequest.getStockCard(),stockCardRequest.getWareHouseId());
    }
    @DeleteMapping(path = "/delete")
    public void deleteProducts(@RequestParam("ids") List<String> ids){
        productService.deleteProducts(ids);
    }
}
