package com.iknow.stocktrackingbe.payload.response.thirdparty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.SalesOrder;
import com.iknow.stocktrackingbe.model.thirdparty.ThirdPartyType;
import com.iknow.stocktrackingbe.payload.response.AddressResponse;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    @JsonProperty("customer_code")
    private String customerCode;
    @JsonProperty("customer_name")
    private String customerName;
    private AddressResponse address;
    @JsonProperty("tel_no")
    private String telNo;
    private String email;
    @JsonProperty("third_party_type")
    private ThirdPartyType thirdPartyType;
}

