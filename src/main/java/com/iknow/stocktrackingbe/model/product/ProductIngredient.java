package com.iknow.stocktrackingbe.model.product;


import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class ProductIngredient extends idGenerator implements Serializable {

    @NotNull
    @Column(name = "name",unique = true)
    private String name;
    private Double milliGramWeight;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Product> products;
    @NotNull
    private Long stockCount;

}
