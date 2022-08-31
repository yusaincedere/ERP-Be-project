package com.iknow.stocktrackingbe.payload.response.thirdparty;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerListResponse {
    private Long id;
    @JsonProperty("customer_code")
    private String customerCode;
    @JsonProperty("customer_name")
    private String customerName;
    @JsonProperty("tel_no")
    private String telNo;
    private String email;

}
