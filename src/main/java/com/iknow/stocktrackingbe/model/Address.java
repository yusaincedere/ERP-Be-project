package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class Address  extends idGenerator implements Serializable {
    @NotNull
    private String country;
    @NotNull
    private String city;
    private String postCode;
    private String district;
    private String street;
    private String details;

}
