package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.PurchaseOrder;
import com.iknow.stocktrackingbe.model.suplier.SupplierType;
import lombok.*;

import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequest {
    @JsonProperty("supplier_code")
    private String supplierCode;
    @JsonProperty("supplier_name")
    private String supplierName;

    @JsonProperty("address_request")
    private AddressRequest addressRequest;
    @JsonProperty("tel_no")
    private String telNo;

    private String email;

    @Enumerated
    @JsonProperty("supplier_type")
    private SupplierType supplierType;



}
