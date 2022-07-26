package com.iknow.stocktrackingbe.model.product;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.prospectus.Prospectus;
import com.iknow.stocktrackingbe.model.stock.StockCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product extends idGenerator implements Serializable {


    private String productCode;

    private String productName;

    private Double productMilligramWeight;

    private LocalDate expiryDate;

    private LocalDate produceDate;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    private String amountOfUsage;

    @OneToOne
    private Prospectus prospectus;

    @OneToOne
    private StockCard stockCard;

    @NotNull
    private Long safetStockCount;


    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<ProductIngredient> productIngredients;


}
