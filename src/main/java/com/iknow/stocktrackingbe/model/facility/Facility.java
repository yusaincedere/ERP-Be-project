package com.iknow.stocktrackingbe.model.facility;

import com.iknow.stocktrackingbe.model.warehouse.Address;
import com.iknow.stocktrackingbe.model.warehouse.WareHouse;
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
public class Facility implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Address address;

    @OneToMany
    private List<WareHouse> wareHouses;


}
