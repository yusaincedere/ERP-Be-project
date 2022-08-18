package com.iknow.stocktrackingbe.model;
import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class PurchaseOrder extends BaseEntity{


    @ManyToOne
    private Supplier supplier;

    @CreatedDate
    private Date createdDate = new Date();

    private Date dueDate;

    private BigDecimal totalPrice;
}
