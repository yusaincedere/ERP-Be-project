package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
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
@Builder(toBuilder = true)
@Entity
public class Production extends idGenerator implements Serializable {

    @Enumerated(EnumType.STRING)
    private ProductionStatus productionStatus = ProductionStatus.EMPTY;

    @NotNull
    private Long productionCount;


    @ManyToOne
    private Product product;



}
