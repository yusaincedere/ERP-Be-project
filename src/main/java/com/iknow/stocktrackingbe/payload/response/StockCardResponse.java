package com.iknow.stocktrackingbe.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCardResponse {
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("stock_count")
    private Long stockCount;
    @JsonProperty("safe_stock_count")
    private Long safeStockCount;
    @JsonProperty("max_stock")
    private Long max;
    @JsonProperty("expected_supply_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate expectedSupplyDate;
    @JsonProperty("ware_house_name")
    private String wareHouseName;
    @JsonProperty("product_name")
    private String productName;

}
