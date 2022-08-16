package com.iknow.stocktrackingbe.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;

import java.math.BigDecimal;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @ManyToOne
    private ProductType productType;
    @ManyToOne
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

    @ManyToOne
    private Dimension dimension;

    @ManyToOne
    private Weight weight;

    private boolean toBuy;

    private boolean toSell;

}
