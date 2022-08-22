package com.iknow.stocktrackingbe.model;
import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class PurchaseOrderDetail extends BaseEntity{

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    private Product product;

    private Long amount;

}
