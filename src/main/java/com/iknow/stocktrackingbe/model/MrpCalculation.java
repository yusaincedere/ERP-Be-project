package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class MrpCalculation extends idGenerator implements Serializable {

    @CreatedDate
    private Date date = new Date();


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
