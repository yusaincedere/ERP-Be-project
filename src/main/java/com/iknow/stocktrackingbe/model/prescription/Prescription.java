package com.iknow.stocktrackingbe.model.prescription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Prescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prescriptionVersion;

    private LocalDate startDate;
    private LocalDate endDate;



    @CreatedDate
    private Date created = new Date();

    private boolean draft = false;

    private boolean approved = false;

    @ManyToMany
    private List<PrescriptionProduct> prescriptionProducts;
}
