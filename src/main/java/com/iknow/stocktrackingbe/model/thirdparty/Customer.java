package com.iknow.stocktrackingbe.model.thirdparty;

import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.SalesOrder;
import lombok.*;

import javax.persistence.Entity;

import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
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
    @Email
    private String email;

    @OneToMany
    private List<SalesOrder> salesOrderList;

    @Enumerated
    private ThirdPartyType thirdPartyType;
}
