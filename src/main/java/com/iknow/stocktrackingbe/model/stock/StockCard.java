package com.iknow.stocktrackingbe.model.stock;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class StockCard extends idGenerator implements Serializable {

    private String stockCode;

    private String name;

    private Long stockCount;
    private Long safeStockCount;
    private Long max;

    private LocalDate expectedSupplyDate;


}
