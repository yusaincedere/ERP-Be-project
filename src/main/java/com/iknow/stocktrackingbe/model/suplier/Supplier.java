package com.iknow.stocktrackingbe.model.suplier;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.PurchaseOrder;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

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

    @Email
    private String email;

    @Enumerated
    private SupplierType supplierType;


    @OneToMany
    private List<PurchaseOrder> orders = new ArrayList<>();


}
