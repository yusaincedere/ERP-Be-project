package com.iknow.stocktrackingbe.model.product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.Stock;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "product", uniqueConstraints={
        @UniqueConstraint( name = "unique_product_name",  columnNames ={"product_name"})
})
public class Product extends BaseEntity{


    private String productCode;
    @NotNull
    @Column(name = "product_name",unique = true)
    private String productName;

    @Enumerated
    private ProductType productType;
    @Enumerated
    private ProductUnit productUnit;


    @OneToMany
    @JsonIgnore
    private List<Stock> stocks;

    private String description;

    private String barcode;

    private String url;

    private String partNumber;

    private BigDecimal selPrice;

    private BigDecimal cost;

    @Enumerated
    private DimensionType dimensionType;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    @Enumerated
    private WeightType weightType;

    private BigDecimal weight;

    private Boolean toBuy;

    private Boolean toSell;

}
