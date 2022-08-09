package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCardRequest {
    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("expected_supply_date")
    private LocalDate expectedSupplyDate;
    @JsonProperty("stock_code")
    @Size(max = 8,min = 8,message = "Stock card number must be 8 digits")
    private String stockCode;
    private String name;
    @JsonProperty("stock_count")
    private Long stockCount ;
    @JsonProperty("safe_stock_count")
    private Long safeStockCount ;
    private Long max;
}
