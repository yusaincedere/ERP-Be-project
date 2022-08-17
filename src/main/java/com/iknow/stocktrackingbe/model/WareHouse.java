package com.iknow.stocktrackingbe.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.product.Product;
import com.sun.jdi.PrimitiveValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class WareHouse extends BaseEntity{

    @NotNull
    private String name;

    @OneToOne
    private Address address;

    @ManyToMany
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    private WareHouse parent;

    @Size(max =20,min = 8)
    private String phone;




}
