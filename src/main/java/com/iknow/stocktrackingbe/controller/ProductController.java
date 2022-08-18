package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.response.StockResponse;
import com.iknow.stocktrackingbe.payload.response.mapper.ProductResponseMapper;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.product.ProductRequest;
import com.iknow.stocktrackingbe.payload.request.product.ProductUpdateRequest;
import com.iknow.stocktrackingbe.payload.response.mapper.StockResponseMapper;
import com.iknow.stocktrackingbe.payload.response.product.ProductResponse;
import com.iknow.stocktrackingbe.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/product")
public class ProductController {
    private final ProductService productService;

    private final ProductResponseMapper productResponseMapper;

    private final StockResponseMapper stockResponseMapper;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts(Pageable page){
            return ResponseEntity.ok(productResponseMapper.mapper(productService.getProducts(page)));
    }
    @GetMapping(path = "/id/{id}")
    public  ResponseEntity<ProductResponse> getProductById(@PathVariable(required = false) Long id){
            return ResponseEntity.ok(productResponseMapper.mapper(productService.getProductById(id)));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<ProductResponse>> searchByProductName(@PathVariable(required = false) String name,Pageable pageable){
        return ResponseEntity.ok(productResponseMapper.mapper(productService.searchByProductName(name,pageable)));
    }

    @GetMapping(path = "/stocks/{id}")
    public ResponseEntity<List<StockResponse>> getProductStocks(@PathVariable Long id){
        return ResponseEntity.ok(stockResponseMapper.mapper(productService.getProductStocks(id)));
    }
    @PostMapping
    public ResponseEntity<?> createNewProduct(@Valid @RequestBody ProductRequest productRequest){
        Product product =productService.createNewProduct(productRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                        .path("/{id}")
                                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
    @PutMapping("/{id}/update")
    public void updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest productUpdateRequest){
        productService.updateProduct(id,productUpdateRequest);
    }


    @DeleteMapping(path = "/delete")
    public void deleteProducts(@RequestBody IdListRequest idList){
        productService.deleteProducts(idList.getIdList());
    }
}
