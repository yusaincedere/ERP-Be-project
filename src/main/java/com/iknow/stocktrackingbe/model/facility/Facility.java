package com.iknow.stocktrackingbe.model.facility;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.warehouse.Address;
import com.iknow.stocktrackingbe.model.warehouse.WareHouse;
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
public class Facility extends idGenerator implements Serializable {

    @NotNull
    private String name;

    @OneToOne
    private Address address;

    @OneToMany
    private Set<WareHouse> wareHouses;


}
