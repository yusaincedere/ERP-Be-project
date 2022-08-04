package com.iknow.stocktrackingbe.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
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
@Builder(toBuilder = true)
@Entity
public class Product extends idGenerator implements Serializable {


    private String productCode;
    @NotNull
    private String productName;

    private Double productMilligramWeight;
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate expiryDate;
    @NotNull

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate produceDate;
    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType = CurrencyType.TL;

    private String amountOfUsage;

    @OneToOne
    private Prospectus prospectus;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StockCard> stockCards;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnore
    private List<Production> productions;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProductIngredient> productIngredients;



}
