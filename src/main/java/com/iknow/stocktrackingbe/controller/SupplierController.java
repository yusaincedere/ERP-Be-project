package com.iknow.stocktrackingbe.controller;
import com.iknow.stocktrackingbe.payload.request.SupplierRequest;
import com.iknow.stocktrackingbe.payload.response.mapper.SupplierListResponseMapper;
import com.iknow.stocktrackingbe.payload.response.mapper.SupplierResponseMapper;
import com.iknow.stocktrackingbe.payload.response.thirdparty.SupplierListResponse;
import com.iknow.stocktrackingbe.payload.response.thirdparty.SupplierResponse;
import com.iknow.stocktrackingbe.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    private SupplierResponseMapper supplierResponseMapper;

    private SupplierListResponseMapper supplierListResponseMapper;

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierListResponse>> getSuppliers(Pageable page){
        return ResponseEntity.ok(supplierListResponseMapper.mapper(supplierService.getSuppliers(page)));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SupplierResponse>getSupplierById(@PathVariable Long id){
        return ResponseEntity.ok(supplierResponseMapper.mapper(supplierService.getSupplierById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody SupplierRequest supplierRequest){
        return ResponseEntity.ok(supplierResponseMapper.mapper(supplierService.createSupplier(supplierRequest)));

    }

    @PutMapping("/{id}/update")
    public void updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequest supplierRequest){
        supplierService.updateSupplier(id,supplierRequest);
    }

    @DeleteMapping(path = "/delete")
    public void deleteSupplier(@RequestParam Set<Long> idList){
        supplierService.deleteSupplier(idList);
    }



}
