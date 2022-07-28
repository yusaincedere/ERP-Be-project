package com.iknow.stocktrackingbe.model.warehouse;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.facility.Facility;
import com.iknow.stocktrackingbe.model.product.Product;
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
public class WareHouse extends idGenerator implements Serializable {

    @NotNull
    private String name;

    @OneToOne
    private Address address;

    @ManyToMany
    private Set<Product> products;

    @ManyToOne
    private Facility facility;



}
