package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.StockCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StockCardRepository extends JpaRepository<StockCard,String> {

    List<StockCard> findAllByWareHouseId(String id);
}
