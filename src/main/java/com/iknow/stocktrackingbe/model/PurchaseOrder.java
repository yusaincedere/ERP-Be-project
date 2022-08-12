package com.iknow.stocktrackingbe.model;
import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
public class PurchaseOrder extends idGenerator implements Serializable {


    @ManyToOne
    private Supplier supplier;

    @CreatedDate
    private Date createdDate = new Date();

    private Date dueDate;

    private BigDecimal totalPrice;
}
