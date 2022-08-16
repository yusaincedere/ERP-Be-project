package com.iknow.stocktrackingbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class Bom  extends BaseEntity {

    private String bomCode;

    private String description;

    private String bomName;

    private boolean approved = false;

    private LocalDate dateApproved;

    @OneToOne
    private User userCreated;
    @OneToOne
    private User userModified;

    @OneToOne
    private User userApproved;

    private double quantity;

    private double efficiency;

    private boolean draft = true;

    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "bom", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<BomDetail> bomDetails;

    @OneToOne
    private Product product;
}
