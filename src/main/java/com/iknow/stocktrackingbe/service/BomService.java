package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.bom.BomDetail;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.model.product.ProductType;
import com.iknow.stocktrackingbe.payload.request.bom.BomDetailRequest;
import com.iknow.stocktrackingbe.payload.request.bom.BomRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.BomDetailRequestMapper;
import com.iknow.stocktrackingbe.payload.request.mapper.BomRequestMapper;
import com.iknow.stocktrackingbe.repository.BomRepository;
import com.iknow.stocktrackingbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class BomService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BomRepository bomRepository;

    private final ProductRepository productRepository;

    private final BomRequestMapper bomRequestMapper;

    private final WareHouseService wareHouseService;

    private final BomDetailService bomDetailService;
    private final BomDetailRequestMapper bomDetailRequestMapper;

    public List<Bom> getBomList(Pageable pageable) {
        logger.info("Service Called: getBomList");
        return bomRepository.findAll(pageable).getContent();
    }

    public List<Bom> getBomListByName(String name, Pageable page) {
        logger.info("Service Called: getBomList");
        return bomRepository.findAllByBomName(name,page).getContent();
    }
    public Bom getBomById(Long id) {
        logger.info("Service Called: geBomById");
        Optional<Bom> optional = bomRepository.findById(id);
        if(optional.isPresent()){
            Bom bom =  optional.get();
            calculateBomCost(bom);
            return bom;
        }else {
            logger.warn("There is no bom with id:"  + id);
            throw new NotFoundException("There is no bom with this id");
        }
    }



    public List<Bom> findBomListByProduct(Product product) {

        if(product.getProductType()== ProductType.raw){
            List<BomDetail> bomDetails = bomDetailService.findBomDetailsByChildId(product.getId());
            List<Bom> bomList = new ArrayList<>();
            for (BomDetail bomDetail:bomDetails){
                bomList.add(bomDetail.getBom());
            }
            return bomList;
        }else {
            return bomRepository.findAllByProductId(product.getId());
        }
    }


    public Bom createBom(BomRequest bomRequest) {
        logger.info("Service Called: createBom");
        if(bomRepository.existsByBomName(bomRequest.getBomName())){
            logger.error("A bom with this name already exists name:" + bomRequest.getBomName());
            throw new IllegalStateException("A bom with this name already exists");
        }else{
            Product mainProduct = productRepository.findById(bomRequest.getProductId()).orElseThrow(() -> new NotFoundException("There is no product with this id"));;
            WareHouse wareHouse = wareHouseService.getWareHouseById(bomRequest.getWareHouseId());
            Bom bom = bomRequestMapper.mapToModel(bomRequest,mainProduct,wareHouse);
            bomRepository.save(bom);
            return bom;
        }
    }
    public Bom createDraft(BomRequest bomRequest) {
        Bom bom =createBom(bomRequest);
        bom.setDraft(true);
        bomRepository.flush();
        return bom;
    }
    public void approveBom(Long id) {
        logger.info("Service Called: approveBom");
        Bom bom = getBomById(id);
        bom.setApproved(true);
        bomRepository.flush();
    }

    public void deleteBom(Set<Long> idList) {
        logger.info("Service Called: deleteBom");
        bomRepository.deleteByIdIn(idList);
    }
    public Bom cloneBom(Long id) {
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
    public void addBomDetail(Long id, BomDetailRequest bomDetailRequest) {
        logger.info("Service Called: addBomDetail");
        if(bomDetailService.existsByProductId(id)){
            logger.error("The product to add is already the product to produce.");
           throw new IllegalStateException("The product to add is already the product to produce.");
        }else{
            Bom bom = getBomById(id);
            Product childProduct = productRepository.findById(bomDetailRequest.getChildProductId()).orElseThrow(() -> new NotFoundException("There is not product with this id"));;
            BomDetail bomDetail = bomDetailRequestMapper.mapToModel(bomDetailRequest,childProduct,bom);
            bom.getBomDetails().add(bomDetail);
            calculateBomCost(bom);
            logger.info("Bom detail created for: " + bom.getBomName());
        }
    }

    public void updateBom(Long id, BomRequest bomRequest) {
        logger.info("Service Called: updateBom");
        Optional<Bom> optional = bomRepository.findById(id);
        if(optional.isPresent()){
            Bom bom = optional.get();
            bom.setBomCode(bomRequest.getBomCode()==null ? optional.get().getBomCode():bomRequest.getBomCode());
            bom.setBomName(bomRequest.getBomName()==null ? optional.get().getBomName():bomRequest.getBomName());
            bom.setDescription(bomRequest.getDescription()==null ? optional.get().getDescription():bomRequest.getDescription());
            bom.setEfficiency(bomRequest.getEfficiency()==null ? optional.get().getEfficiency():bomRequest.getEfficiency());
            bom.setEndDate(bomRequest.getEndDate()==null ? optional.get().getEndDate():bomRequest.getEndDate());
            bom.setStartDate(bomRequest.getStartDate()==null ? optional.get().getStartDate():bomRequest.getStartDate());
            bom.setQuantity(bomRequest.getQuantity()==null ? optional.get().getQuantity():bomRequest.getQuantity());
            logger.info("Bom updated");
        }else {
            logger.error("There is no bom with id: " + id);
            throw new NotFoundException("There is no bom with this id");
        }
    }
    public void updateBomDetail(Long id, BomDetailRequest bomDetailRequest) {
        bomDetailService.updateBomDetail(id,bomDetailRequest);
    }

    public void calculateBomCost(Bom bom) {
        BigDecimal detailCost = new BigDecimal(0);
        BigDecimal bomCost = new BigDecimal(0);
        bom.setUnitCost(bomCost);
        bom.setTotalCost(bomCost);
        for (BomDetail bomDetail : bom.getBomDetails()) {
            bomDetail.setBomDetailCost(detailCost);
            bomDetail.setTotalBomDetailCost(detailCost);
            detailCost = bomDetail.getQuantity().multiply(bomDetail.getEfficiency()).multiply(bomDetail.getChildProduct().getCost());
            bomDetail.setBomDetailCost(detailCost);
            bomCost = bomCost.add(detailCost);
            bomDetail.setTotalBomDetailCost(detailCost.multiply(bom.getQuantity()));
        }
        bom.setTotalCost(bomCost.multiply(bom.getQuantity()));
        bom.setUnitCost(bomCost);
    }
}
