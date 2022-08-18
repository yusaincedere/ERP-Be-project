package com.iknow.stocktrackingbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class Stock extends BaseEntity{

    @ManyToOne
    @JsonIgnore
    private Product product;

    private BigDecimal stock;

    private BigDecimal minStock;

    private BigDecimal orderQuantity;

    @ManyToOne
    @JsonIgnore
    private WareHouse wareHouse;
}
