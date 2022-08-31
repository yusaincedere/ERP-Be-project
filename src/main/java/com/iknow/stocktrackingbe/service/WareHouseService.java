package com.iknow.stocktrackingbe.service;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.Stock;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.request.StockRequest;
import com.iknow.stocktrackingbe.payload.request.WareHouseRequest;
import com.iknow.stocktrackingbe.payload.request.mapper.AddressRequestMapper;
import com.iknow.stocktrackingbe.payload.request.mapper.StockRequestMapper;
import com.iknow.stocktrackingbe.payload.request.mapper.WareHouseRequestMapper;
import com.iknow.stocktrackingbe.repository.ProductRepository;
import com.iknow.stocktrackingbe.repository.WareHouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WareHouseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WareHouseRepository wareHouseRepository;

    private final ProductRepository productRepository;

    private final WareHouseRequestMapper wareHouseRequestMapper;

    private final StockRequestMapper stockRequestMapper;

    private final StockService stockService;

    private final AddressRequestMapper addressRequestMapper;

    public WareHouse getWareHouseById(Long id){
        logger.info("Service Called: getWareHouseById");
        Optional<WareHouse> optional = wareHouseRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new NotFoundException("There is no warehouse with this id");
        }
    }

    public List<WareHouse> getWareHouses(Pageable page){
        Page<WareHouse> wareHousesPage = wareHouseRepository.findAll(page);
        if(!wareHousesPage.getContent().isEmpty()){
          return wareHousesPage.getContent();
        }else {
            throw new NotFoundException("There is no warehouse");
        }
    }
    public void createWareHouse(WareHouseRequest wareHouseRequest) {
        logger.info("Service Called: createWareHouse");
        WareHouse wareHouse = wareHouseRequestMapper.mapToModel(wareHouseRequest);
        if(wareHouseRequest.getParentId()!=null){
            WareHouse parent = getWareHouseById(wareHouseRequest.getParentId());
            wareHouse.setParent(parent);
        }
        wareHouseRepository.save(wareHouse);
    }


    public List<WareHouse> getWareHosesByIds(Set<Long> wareHouseIds) {
        logger.info("Service Called: getWareHosesByIds");
        return wareHouseRepository.findAllById(wareHouseIds);
    }

    public List<WareHouse> getAllChildWarehouses(Long parentId) {
        return wareHouseRepository.findAllByParentId(parentId);
    }
    public List<Stock> getWareHouseStocks(Long id) {
        WareHouse wareHouse = getWareHouseById(id);
        return wareHouse.getStocks();
    }

    public void updateWareHouse(Long id, WareHouseRequest wareHouseRequest) {
        logger.info("Service Called: updateWareHouse");
        Optional<WareHouse> optional = wareHouseRepository.findById(id);
        if(optional.isPresent()){
            WareHouse wareHouse = optional.get();
            wareHouse.setAddress(wareHouseRequest.getAddress()==null ? optional.get().getAddress():addressRequestMapper.mapToModel(wareHouseRequest.getAddress()));
            wareHouse.setName(wareHouseRequest.getName()==null ? optional.get().getName():wareHouseRequest.getName());
            wareHouse.setPhone(wareHouseRequest.getPhone()==null ? optional.get().getPhone():wareHouseRequest.getPhone());

            if(wareHouseRequest.getParentId()!=null){
                WareHouse parent = getWareHouseById(wareHouseRequest.getParentId());
                wareHouse.setParent(parent);
            }else {
                logger.error("Parent warehouse not found");
                throw new NotFoundException("Error while adding parent warehouse");
            }
        }else {
            logger.error("There is no warehouse with id: " + id);
            throw new NotFoundException("There is no warehouse with this id");
        }
    }

    public void addStockToWareHouse(Long id, StockRequest stockRequest) {
        logger.info("Service Called: updateWareHouse");
        WareHouse wareHouse = getWareHouseById(id);
        if(stockService.existsByProductId(stockRequest.getProductId(),wareHouse.getId())){
            throw new IllegalStateException("This product already exists in this warehouse");
        }else{
            Product product = productRepository.findById(stockRequest.getProductId()).orElseThrow(() -> new NotFoundException("There is no product with this id"));;
            Stock stock = stockRequestMapper.mapToModel(stockRequest,wareHouse,product);
            wareHouse.getStocks().add(stock);
            product.getStocks().add(stock);
            wareHouseRepository.flush();
            logger.info("Stock added to warehouse: " + wareHouse.getName());
        }
    }

    public void deleteStockFromWareHouse(Long wareHoseId, IdListRequest idListRequest){
        WareHouse wareHouse = getWareHouseById(wareHoseId);
        List<Stock> stocks = stockService.getAllStocksByIdList(idListRequest.getIdList());
        wareHouse.getStocks().removeAll(stocks);
        for(Stock stock:stocks){
            stock.getProduct().getStocks().remove(stock);
            stockService.delteStock(stock);
        }
        wareHouseRepository.flush();
    }
    public void deleteWareHouses(Set<Long> idList) {
        logger.info("Service Called: deleteWareHouses");
        wareHouseRepository.deleteByIdIn(idList);
        logger.info("Ware houses  deleted");
    }



}
