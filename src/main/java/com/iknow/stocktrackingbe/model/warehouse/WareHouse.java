package com.iknow.stocktrackingbe.model.warehouse;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.facility.Facility;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class WareHouse extends idGenerator implements Serializable {

    private String name;

    @OneToOne
    private Address address;

    @ManyToMany
    private List<Product> products;

    @ManyToOne
    private Facility facility;



}
