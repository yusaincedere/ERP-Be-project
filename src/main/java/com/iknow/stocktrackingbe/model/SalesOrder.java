package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.thirdparty.Customer;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class SalesOrder extends BaseEntity{

    @ManyToOne
    private Customer customer;

    @CreatedDate
    private Date date = new Date();


    private Date dueDate;

    private BigDecimal totalPrice;

}
