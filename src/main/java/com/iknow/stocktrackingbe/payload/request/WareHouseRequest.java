package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Address;
import com.iknow.stocktrackingbe.model.WareHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseRequest {

    @NotEmpty
    private String name;


    private Address address;


    @JsonProperty("parent_id")
    private String parentId;


    @Size(max =20,min = 8)
    private String phone;
}
