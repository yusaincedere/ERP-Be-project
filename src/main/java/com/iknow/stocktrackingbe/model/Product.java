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
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "product", uniqueConstraints={
        @UniqueConstraint( name = "unique_product_name",  columnNames ={"product_name"})
})
public class Product extends idGenerator implements Serializable {


    private String productCode;
    @NotNull
    @Column(name = "product_name",unique = true)
    private String productName;

    @ManyToOne
    private ProductType productType;
    @ManyToOne
    private ProductUnit productUnit;

    @OneToOne
    private Product parentProduct;

    @OneToMany
    private List<Stock> stocks;


    @OneToMany
    private List<Product> childProducts = new ArrayList<>();
}
