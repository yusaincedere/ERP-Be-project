package com.iknow.stocktrackingbe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class StockCard extends BaseEntity{


    @JsonProperty("stock_code")
    private String stockCode;

    private String name;

    @JsonProperty("stock_count")
    private Long stockCount = 50L;
    @JsonProperty("safe_stock_count")
    private Long safeStockCount = 0L;
    private Long max;

    @JsonProperty("expected_supply_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate expectedSupplyDate;

    @ManyToOne
    @JsonIgnore
    private WareHouse wareHouse;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @Enumerated(EnumType.STRING)
    private StockCardStatus stockCardStatus = StockCardStatus.WAITING;


}
