package com.iknow.stocktrackingbe.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
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
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate expectedSupplyDate;
    @JsonProperty("ware_house_name")
    private String wareHouseName;
    @JsonProperty("product_name")
    private String productName;

}
