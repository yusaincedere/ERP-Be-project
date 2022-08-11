package com.iknow.stocktrackingbe.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class WareHouse extends idGenerator implements Serializable {

    @NotNull
    private String name;

    @OneToOne
    private Address address;

    @ManyToMany
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    @ManyToOne()
    @JsonIgnore
    private Facility facility;



}
