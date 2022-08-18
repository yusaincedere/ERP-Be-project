package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class Customer extends BaseEntity{

    private String customerCode;
    private String customerName;
    @OneToOne
    private Address address;
    private String telNo;

    @OneToMany
    private List<SalesOrder> salesOrderList;
}
