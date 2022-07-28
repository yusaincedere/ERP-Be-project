package com.iknow.stocktrackingbe.model.production;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Production extends idGenerator implements Serializable {

    @Enumerated(EnumType.STRING)
    private ProductionStatus productionStatus = ProductionStatus.EMPTY;

    @NotNull
    private Long productionCount;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
    })
    private Product product;




}
