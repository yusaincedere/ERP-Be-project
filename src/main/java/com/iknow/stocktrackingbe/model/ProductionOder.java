package com.iknow.stocktrackingbe.model;
import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.user.User;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class ProductionOder extends BaseEntity {

    private String note;

    @ManyToOne
    private WareHouse wareHouse;

    private LocalDate dateValid;

    @ManyToOne
    private User userCreated;

    @ManyToOne
    private User userValid;

    private ProductionStatus productionStatus;

    private BigDecimal bigDecimal;

    private Boolean approved;

    @ManyToOne
    private Bom bom;

}
