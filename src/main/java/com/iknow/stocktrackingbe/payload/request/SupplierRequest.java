package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.thirdparty.ThirdPartyType;
import lombok.*;

import javax.persistence.Enumerated;

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
    private ThirdPartyType thirdPartyType;



}
