package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.StockCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface StockCardRepository extends JpaRepository<StockCard,String> {

    Set<StockCard> findAllByWareHouseId(String id);

    Page<StockCard> findAllByWareHouseId(String id,Pageable pageable);

    Page<StockCard> findAllByWareHouseAddressCity(String city,Pageable pageable);

    Page<StockCard> findAllByWareHouseAddressCountry(String country,Pageable pageable);

    boolean existsByStockCode(String stockCode);
}
