package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    boolean existsByProductIdAndWareHouseId(Long productId,Long wareHouseId);
}
