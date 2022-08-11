package com.iknow.stocktrackingbe.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "product_ingredient", uniqueConstraints={
        @UniqueConstraint( name = "unique_name",  columnNames ={"name"})
})
public class ProductIngredient extends idGenerator implements Serializable {

    @NotNull
    @Column(name = "name",unique = true)
    private String name;
    private Double milliGramWeight;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;
    @NotNull
    private Long stockCount;
}
