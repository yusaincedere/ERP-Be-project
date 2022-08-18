package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class Address  extends BaseEntity{
    @NotNull
    private String country;
    @NotNull
    private String city;
    private String postCode;
    private String district;
    private String street;
    private String details;

}
