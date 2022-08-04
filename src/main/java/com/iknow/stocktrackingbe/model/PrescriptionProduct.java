package com.iknow.stocktrackingbe.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class PrescriptionProduct extends idGenerator implements Serializable {


    private int quantity;
    @ManyToOne
    @JsonIgnore
    private Product product;

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("usage_descriptions")
    private String usageDescriptions;

    @NotNull
    @JsonProperty("start_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate startDate;
    @NotNull
    @JsonProperty("end_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Prescription prescription;


}
