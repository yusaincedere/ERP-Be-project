package com.iknow.stocktrackingbe.model.product;

import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.product.WeightType;
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
public class Weight extends BaseEntity {

    @Enumerated
    private WeightType weightType;
    private BigDecimal amount;
}
