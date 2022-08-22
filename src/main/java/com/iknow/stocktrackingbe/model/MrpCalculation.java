package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.bom.BomDetail;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class MrpCalculation extends BaseEntity{

    @CreatedDate
    private LocalDate date = LocalDate.now();


    @OneToOne
    private SalesOrderDetail salesOrderDetail;

    @OneToOne
    private BomDetail bomDetail;

    @OneToOne
    private Bom bom;

    @OneToOne
    private Product mainProduct;

    @OneToOne
    private Product childProduct;

    private Long mainProductAmount;

    private Long mainAmount;

    private Long givenOrderAmount;

    private Long stockCount;

    private Long requirement;

    @Enumerated(EnumType.STRING)
    private SupplyType supplyType;

}
