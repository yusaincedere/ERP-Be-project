package com.iknow.stocktrackingbe.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
    private String country;
    private String city;
    @JsonProperty("post_code")
    private String postCode;
    private String district;
    private String street;
}
