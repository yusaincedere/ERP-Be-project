package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "product_unit", uniqueConstraints={
        @UniqueConstraint( name = "unique_unit_name",  columnNames ={"unit_name"})
})
public class ProductUnit extends idGenerator implements Serializable {
    @Column(name = "unit_name",unique = true)
    private String unitName;
}
