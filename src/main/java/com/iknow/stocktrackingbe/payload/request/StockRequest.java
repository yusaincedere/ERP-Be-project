package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.WareHouse;
import com.iknow.stocktrackingbe.model.product.Product;
import lombok.*;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockRequest {
    @JsonProperty("product_id")
    private Long productId;

    private BigDecimal stock;
    @JsonProperty("min_stock")
    private BigDecimal minStock;
    @JsonProperty("order_quantity")
    private BigDecimal orderQuantity;
}
