package com.iknow.stocktrackingbe.model.bom;

import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class BomDetail extends BaseEntity{

    @ManyToOne
    private Bom bom;


    @OneToOne
    private  Product mainProduct;

    @OneToOne
    private Product childProduct;


    private BigDecimal quantity;


    private String description;

    private BigDecimal bomDetailCost = new BigDecimal(0);

    private BigDecimal totalBomDetailCost = new BigDecimal(0);


    private BigDecimal efficiency;

}
