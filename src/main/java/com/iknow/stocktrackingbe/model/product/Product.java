package com.iknow.stocktrackingbe.model.product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.production.Production;
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
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product extends idGenerator implements Serializable {


    private String productCode;
    @NotNull
    private String productName;

    private Double productMilligramWeight;
    @NotNull
    private LocalDate expiryDate;
    @NotNull
    private LocalDate produceDate;
    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType = CurrencyType.TL;

    private String amountOfUsage;

    @OneToOne
    private Prospectus prospectus;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private StockCard stockCard;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnore
    private List<Production> productions;


    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<ProductIngredient> productIngredients;


}
