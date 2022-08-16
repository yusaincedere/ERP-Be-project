package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
