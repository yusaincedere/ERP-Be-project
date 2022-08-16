package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class BomDetail extends BaseEntity{

    @ManyToOne
    private Bom bom;


    @OneToOne
    private  Product mainProduct;

    @OneToOne
    private  Product childProduct;


    private BigDecimal quantity;


    private String description;


    private BigDecimal efficiency;








}
