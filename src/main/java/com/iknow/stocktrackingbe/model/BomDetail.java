package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class BomDetail extends idGenerator implements Serializable {

    @OneToOne
    private Bom bom;


    @OneToOne
    private Product mainProduct;

    @OneToOne
    private Product childProduct;


    private Long mainAmount;


    private Long childAmount;

}
