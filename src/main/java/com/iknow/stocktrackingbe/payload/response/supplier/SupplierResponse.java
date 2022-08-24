package com.iknow.stocktrackingbe.payload.response.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.suplier.SupplierType;
import com.iknow.stocktrackingbe.payload.response.AddressResponse;
import lombok.*;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse {
    @JsonProperty("supplier_code")
    private String supplierCode;
    @JsonProperty("supplier_name")
    private String supplierName;
    @JsonProperty("address")
    private AddressResponse addressResponse;
    @JsonProperty("tel_no")
    private String telNo;
    private String email;
    @JsonProperty("supplier_type")
    private SupplierType supplierType;
    private Long id;
}
