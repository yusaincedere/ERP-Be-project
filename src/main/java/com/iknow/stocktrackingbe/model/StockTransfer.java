package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockTransfer extends BaseEntity {
    private LocalDate dueDate;
    private WareHouse baseWareHouse;
    private WareHouse targetWareHose;
    private Stock stock;
    private BigDecimal stockNumber;
    private Boolean isApproved = false;
}
