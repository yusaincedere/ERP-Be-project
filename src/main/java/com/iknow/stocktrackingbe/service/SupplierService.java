package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.model.suplier.Supplier;
import com.iknow.stocktrackingbe.payload.request.SupplierRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.AddressRequestMapper;
import com.iknow.stocktrackingbe.payload.request.mapper.SupplierRequestMapper;
import com.iknow.stocktrackingbe.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AddressRequestMapper addressRequestMapper;

    private final SupplierRequestMapper supplierRequestMapper;
    public List<Supplier> getSuppliers(Pageable page) {
        logger.info("Service Called: getSuppliers");
        return supplierRepository.findAll(page).getContent();

    }

    public Supplier getSupplierById(Long id) {
        logger.info("Service Called: getSupplierById");
        Optional<Supplier> optional = supplierRepository.findById(id);
        if(optional.isPresent()){
           return  optional.get();
        }else {
            logger.error("There is no supplier with id:" + id);
            throw new NotFoundException("There is no supplier with this id");
        }
    }

    public Supplier createSupplier(SupplierRequest supplierRequest) {
        logger.info("Service Called: createSupplier");
        if(supplierRepository.existsBySupplierCodeAndSupplierName(supplierRequest.getSupplierCode(),supplierRequest.getSupplierName())){
            logger.error("A supplier with this name  or code already exists  code: " + supplierRequest.getSupplierCode()+" name: " + supplierRequest.getSupplierName());
            throw new IllegalStateException("A supplier with this name  or code already exists");
        }else{
            Supplier supplier = supplierRequestMapper.mapToModel(supplierRequest);
            supplierRepository.save(supplier);
            return supplier;
        }
    }

    public void updateSupplier(Long id, SupplierRequest supplierRequest) {
        logger.info("Service Called: updateSupplier");
        Optional<Supplier> optional = supplierRepository.findById(id);
        if(optional.isPresent()){
            Supplier supplier = optional.get();
            supplier.setSupplierCode(supplierRequest.getSupplierCode()==null ? optional.get().getSupplierCode():supplierRequest.getSupplierCode());
            supplier.setSupplierName(supplierRequest.getSupplierName()==null ? optional.get().getSupplierName():supplierRequest.getSupplierName());
            supplier.setSupplierType(supplierRequest.getSupplierType()==null ? optional.get().getSupplierType():supplierRequest.getSupplierType());
            supplier.setAddress(supplierRequest.getAddressRequest()==null ? optional.get().getAddress():addressRequestMapper.mapToModel(supplierRequest.getAddressRequest()));
            supplier.setEmail(supplierRequest.getEmail()==null ? optional.get().getEmail():supplierRequest.getEmail());
            supplier.setTelNo(supplierRequest.getTelNo()==null ? optional.get().getTelNo():supplierRequest.getTelNo());
            logger.info("Supplier updated");
        }else {
            logger.error("There is no supplier with id: " + id);
            throw new NotFoundException("There is no supplier with this id");
        }
    }
}
