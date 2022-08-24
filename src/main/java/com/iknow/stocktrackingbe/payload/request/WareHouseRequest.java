package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.WareHouse;
import lombok.*;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseRequest {

    @NotEmpty
    private String name;


    private AddressRequest address;


    @JsonProperty("parent_id")
    private Long parentId;


    @Size(max =20,min = 8)
    private String phone;
}
