package com.iknow.stocktrackingbe.model.prescription;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Prescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prescriptionVersion;

    private LocalDate startDate;
    private LocalDate endDate;


    @CreatedDate
    private LocalDate created;

    private boolean unOfficial;

    private boolean approved;


}
