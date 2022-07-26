package com.iknow.stocktrackingbe.model.production;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Production extends idGenerator implements Serializable {

    @Enumerated(EnumType.STRING)
    private ProductionStatus productionStatus = ProductionStatus.EMPTY;

    private Long productionCount;


    @OneToOne
    private Product product;

}
