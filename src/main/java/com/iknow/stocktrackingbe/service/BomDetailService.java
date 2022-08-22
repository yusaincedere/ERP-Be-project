package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.bom.BomDetail;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.request.bom.BomDetailRequest;
import com.iknow.stocktrackingbe.repository.BomDetailRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BomDetailService {
    private final BomDetailRepository bomDetailRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    public List<BomDetail> findBomDetailsByChildId(Long childProductId){
        return  bomDetailRepository.findAllByChildProductId(childProductId);
    }


    public boolean existsByProductId(Long productId){
        return bomDetailRepository.existsByChildProductId(productId);
    }


    public void updateBomDetail(Long bomDetailId, BomDetailRequest bomDetailRequest){
        logger.info("Service Called: updateBomDetail");
        Optional<BomDetail> optional = bomDetailRepository.findById(bomDetailId);
        if(optional.isPresent()){
            BomDetail bomDetail = optional.get();
            bomDetail.setEfficiency(bomDetailRequest.getEfficiency()==null ? optional.get().getEfficiency():bomDetailRequest.getEfficiency());
            bomDetail.setDescription(bomDetailRequest.getDescription()==null ? optional.get().getDescription():bomDetailRequest.getDescription());
            bomDetail.setQuantity(bomDetailRequest.getQuantity()==null ? optional.get().getQuantity():bomDetailRequest.getQuantity());
            logger.info("BomDetail  updated");
        }else {
            logger.error("There is no bom with id: " + bomDetailId);
            throw new NotFoundException("There is no bom with this id");
        }
    }
}
