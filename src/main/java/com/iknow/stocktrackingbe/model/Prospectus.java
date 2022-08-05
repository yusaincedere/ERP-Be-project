package com.iknow.stocktrackingbe.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class Prospectus extends idGenerator implements Serializable {


    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date created = new Date();

    private String details;


}
