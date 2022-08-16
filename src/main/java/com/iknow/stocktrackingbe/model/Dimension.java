package com.iknow.stocktrackingbe.model;


import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class Dimension extends BaseEntity {

    @Enumerated
    private DimensionType dimensionType;
    private BigDecimal length;
    private BigDecimal height;
    private BigDecimal width;

}
