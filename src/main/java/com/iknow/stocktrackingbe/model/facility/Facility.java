package com.iknow.stocktrackingbe.model.facility;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.warehouse.Address;
import com.iknow.stocktrackingbe.model.warehouse.WareHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Facility extends idGenerator implements Serializable {

    
    private String name;

    @OneToOne
    private Address address;

    @OneToMany
    private List<WareHouse> wareHouses;


}
