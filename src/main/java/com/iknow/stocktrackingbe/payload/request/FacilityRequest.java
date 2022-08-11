package com.iknow.stocktrackingbe.payload.request;

import com.iknow.stocktrackingbe.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityRequest {

    private String name;
    @NotNull
    private Address address;
}