package com.iknow.stocktrackingbe.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCardResponseProduct {
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("stock_count")
    private Long stockCount;
    private Long id;
}
