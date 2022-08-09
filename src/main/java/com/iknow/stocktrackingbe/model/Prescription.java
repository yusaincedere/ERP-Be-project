package com.iknow.stocktrackingbe.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "prescription", uniqueConstraints={
        @UniqueConstraint( name = "version_name",  columnNames ={"prescription_version"})
})
public class Prescription extends idGenerator implements Serializable {

    @Column(name = "prescription_version",unique = true)
    @JsonProperty("prescription_version")
    private String prescriptionVersion;
    @NotNull
    @JsonProperty("start_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
    @JsonProperty("end_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date created = new Date();


    private boolean draft = false;


    private boolean approved = false;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("prescription_products")
    private List<PrescriptionProduct> prescriptionProducts =new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private WareHouse wareHouse;



}
