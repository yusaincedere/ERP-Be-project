package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class Supplier extends BaseEntity{

    private String supplierCode;

    private String supplierName;
    @OneToOne
    private Address address;

    private String telNo;
}
