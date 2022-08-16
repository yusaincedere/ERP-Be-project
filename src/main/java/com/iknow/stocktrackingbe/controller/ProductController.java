package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.payload.response.mapper.ProductResponseMapper;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.ProductRequest;
import com.iknow.stocktrackingbe.payload.request.ProductUpdateRequest;
import com.iknow.stocktrackingbe.payload.response.ProductResponse;
import com.iknow.stocktrackingbe.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/product")
public class ProductController {
    private final ProductService productService;

    private final ProductResponseMapper productResponseMapper;





    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts(Pageable page){
            return ResponseEntity.ok(productResponseMapper.mapper(productService.getProducts(page)));
    }
    @GetMapping(path = "/id/{id}")
    public  ResponseEntity<ProductResponse> getProductById(@PathVariable(required = false) String id){
            return ResponseEntity.ok(productResponseMapper.mapper(productService.getProductById(id)));
    }

    @GetMapping(path = "/name/{name}")
    public  ResponseEntity<List<ProductResponse>> searchByProductName(@PathVariable(required = false) String name,Pageable pageable){
        return ResponseEntity.ok(productResponseMapper.mapper(productService.searchByProductName(name,pageable)));
    }
    @PostMapping
    public void createNewProduct(@Valid @RequestBody ProductRequest productRequest){
        productService.createNewProduct(productRequest);
    }
    @PutMapping("/{id}/update")
    public void updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductUpdateRequest productUpdateRequest){
        productService.updateProduct(id,productUpdateRequest);
    }


    @DeleteMapping(path = "/delete")
    public void deleteProducts(@RequestBody IdListRequest idList){
        productService.deleteProducts(idList.getIdList());
    }
}
