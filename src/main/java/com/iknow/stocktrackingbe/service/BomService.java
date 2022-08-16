package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Bom;
import com.iknow.stocktrackingbe.model.BomDetail;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.payload.request.BomDetailRequest;
import com.iknow.stocktrackingbe.payload.request.BomRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.BomDetailRequestMapper;
import com.iknow.stocktrackingbe.payload.request.mapper.BomRequestMapper;
import com.iknow.stocktrackingbe.repository.BomDetailRepository;
import com.iknow.stocktrackingbe.repository.BomRepository;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class BomService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BomRepository bomRepository;

    private final ProductService productService;

    private final BomRequestMapper bomRequestMapper;
    private final BomDetailRequestMapper bomDetailRequestMapper;

    private final BomDetailRepository bomDetailRepository;

    public List<Bom> getBomList(Pageable pageable) {
        logger.info("Service Called: getBomList");
        return bomRepository.findAll(pageable).getContent();
    }

    public List<Bom> getBomListByName(String name, Pageable page) {
        logger.info("Service Called: getBomList");
        return bomRepository.findAllByBomName(name,page).getContent();
    }
    public Bom getBomById(String id) {
        logger.info("Service Called: geBomById");
        Optional<Bom> optional = bomRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new NotFoundException("There is no bom with this id");
        }
    }


    public Bom createBom(BomRequest bomRequest) {
        logger.info("Service Called: createBom");
        List<BomDetail> bomDetailList = new ArrayList<>();
        Product mainProduct = productService.getProductById(bomRequest.getProductId());
        Bom bom = bomRequestMapper.mapToModel(bomRequest,mainProduct);
        bomRepository.save(bom);
        for(BomDetailRequest bomDetailRequest:bomRequest.getBomDetailRequest()){
            Product childProduct = productService.getProductById(bomDetailRequest.getChildProductId());
            BomDetail bomDetail = bomDetailRequestMapper.mapToModel(bomDetailRequest,mainProduct,childProduct,bom);
            bomDetailList.add(bomDetail);
        }
        bom.setBomDetails(bomDetailList);
        bomRepository.flush();
        return bom;
    }
    public Bom createDraft(BomRequest bomRequest) {
        Bom bom =createBom(bomRequest);
        bom.setDraft(true);
        bomRepository.flush();
        return bom;
    }
    public void approveBom(String id) {
        logger.info("Service Called: approveBom");
        Bom bom = getBomById(id);
        bom.setApproved(true);
        bomRepository.flush();
    }

    public void deleteBom(Set<String> idList) {
        logger.info("Service Called: deleteBom");
        bomRepository.deleteByIdIn(idList);

    }

    public Bom cloneBom(String id) {
        logger.info("Service Called: clonePrescription");
        Bom bom = getBomById(id);
        return new Bom().toBuilder()
                .bomName(bom.getBomName())
                .product(bom.getProduct())
                .startDate(bom.getStartDate())
                .endDate(bom.getEndDate())
                .bomCode(bom.getBomCode())
                .bomDetails(bom.getBomDetails())
                .build();
    }




}
