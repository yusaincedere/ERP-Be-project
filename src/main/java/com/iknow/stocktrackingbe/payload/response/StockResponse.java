package com.iknow.stocktrackingbe.payload.response;


import com.iknow.stocktrackingbe.model.Address;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponse {
    private Long stockId;
    private Long productId;
    private String productName;
    private BigDecimal stock;
    private BigDecimal minStock;
    private BigDecimal orderQuantity;
    private Long wareHouseId;
    private String wareHouseName;
}
