package com.iknow.stocktrackingbe.model.bom;

import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.user.User;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class Bom  extends BaseEntity {

    private String bomCode;

    private String description;

    private String bomName;

    private Boolean approved = false;

    private LocalDate dateApproved;

    @OneToOne
    private User userCreated;
    @OneToOne
    private User userModified;

    @OneToOne
    private User userApproved;

    private BigDecimal quantity;

    private BigDecimal efficiency;

    private Boolean draft = true;

    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "bom", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<BomDetail> bomDetails = new ArrayList<>();


    @ManyToOne
    private WareHouse wareHouse;

    @OneToOne
    private Product product;
}
