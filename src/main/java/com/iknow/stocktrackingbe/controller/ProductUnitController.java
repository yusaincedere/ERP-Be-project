package com.iknow.stocktrackingbe.controller;
import com.iknow.stocktrackingbe.model.ProductUnit;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.ProductUnitRequest;
import com.iknow.stocktrackingbe.service.ProductUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/api/productUnit")
@RequiredArgsConstructor
public class ProductUnitController {
    private final ProductUnitService productUnitService;



    @GetMapping("/units")
    public ResponseEntity<List<ProductUnit>> getProductUnits(){
        return ResponseEntity.ok(productUnitService.getProductUnits());
    }


    @GetMapping("/types/{unit}")
    public ResponseEntity<ProductUnit> getProductUnitByUnitName(@PathVariable String unit){
        return ResponseEntity.ok(productUnitService.getProductUnitByUnitName(unit));
    }

    @PostMapping()
    public void saveUnit(@Valid @RequestBody ProductUnitRequest productUnitRequest){
        productUnitService.saveUnit(productUnitRequest);
    }
    @PutMapping("/{id}/update")
    public void updateUnit(@PathVariable String id,@Valid @RequestBody ProductUnitRequest productUnitRequest){
        productUnitService.updateUnit(id,productUnitRequest);
    }

    @DeleteMapping(path = "/delete")
    public void deleteProductUnits(@RequestBody IdListRequest idList){
        productUnitService.deleteProductUnits(idList.getIdList());
    }
}
