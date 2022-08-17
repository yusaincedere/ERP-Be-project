package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class Stock extends BaseEntity{

    @ManyToOne
    private Product product;

    private BigDecimal stock;

    private BigDecimal minStock;

    private BigDecimal orderQuantity;

    @ManyToOne
    private WareHouse wareHouse;
}
