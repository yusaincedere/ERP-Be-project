package com.iknow.stocktrackingbe.repository.stock;

import com.iknow.stocktrackingbe.model.stock.StockCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCardRepository extends JpaRepository<StockCard,Long> {
}
