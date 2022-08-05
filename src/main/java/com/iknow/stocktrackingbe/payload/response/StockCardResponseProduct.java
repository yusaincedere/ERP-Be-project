package com.iknow.stocktrackingbe.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCardResponseProduct {
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("stock_count")
    private Long stockCount;
    private String id;
}
