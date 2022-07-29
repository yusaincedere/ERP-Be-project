package com.iknow.stocktrackingbe.model.prescription;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PrescriptionProduct extends idGenerator implements Serializable {


    private int quantity;
    @ManyToOne
    private Product product;

    private String usageDescriptions;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

}
