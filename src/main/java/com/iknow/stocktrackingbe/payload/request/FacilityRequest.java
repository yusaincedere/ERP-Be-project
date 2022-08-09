package com.iknow.stocktrackingbe.payload.request;

import com.iknow.stocktrackingbe.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private Address address;
}