package com.iknow.stocktrackingbe.payload.response.thirdparty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierListResponse {
    private Long id;
    @JsonProperty("supplier_code")
    private String supplierCode;
    @JsonProperty("supplier_name")
    private String supplierName;
    @JsonProperty("tel_no")
    private String telNo;

}
