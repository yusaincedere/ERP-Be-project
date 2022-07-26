package com.iknow.stocktrackingbe.repository.warehouse;

import com.iknow.stocktrackingbe.model.warehouse.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse,String> {
}
