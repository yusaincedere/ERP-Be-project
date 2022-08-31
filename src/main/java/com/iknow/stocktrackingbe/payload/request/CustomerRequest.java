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
public class CustomerRequest {
    @JsonProperty("customer_code")
    private String customerCode;
    @JsonProperty("customer_name")
    private String customerName;

    private AddressRequest address;
    @JsonProperty("tel_no")
    private String telNo;

    private String email;
    @Enumerated
    @JsonProperty("third_party_type")
    private ThirdPartyType thirdPartyType;
}
