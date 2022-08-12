package com.iknow.stocktrackingbe.controller;
import com.iknow.stocktrackingbe.model.ProductType;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.ProductTypeRequest;
import com.iknow.stocktrackingbe.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/api/productType")
@RequiredArgsConstructor
public class ProductTypeController {
   private final ProductTypeService productTypeService;




   @GetMapping("/types")
   public ResponseEntity<List<ProductType>> getProductTypes(){
      return ResponseEntity.ok(productTypeService.getProductTypes());
   }


   @GetMapping("/types/{type}")
   public ResponseEntity<ProductType> getProductTypeByTypeName(@PathVariable String type){
      return ResponseEntity.ok(productTypeService.getProductTypeByTypeName(type));
   }

   @PostMapping()
   public void saveType(@Valid @RequestBody ProductTypeRequest productTypeRequest){
      productTypeService.saveType(productTypeRequest);
   }

   @PutMapping("/{id}/update")
   public void updateType(@PathVariable String id, @Valid @RequestBody ProductTypeRequest productTypeRequest){
      productTypeService.updateType(id,productTypeRequest);
   }

   @DeleteMapping(path = "/delete")
   public void deleteProductTypes(@RequestBody IdListRequest idList){
      productTypeService.deleteProductTypes(idList.getIdList());
   }

}
