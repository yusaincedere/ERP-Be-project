package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCardAddRequest {
    @JsonProperty("stock_card")
    @Valid
    private StockCardRequest stockCardRequest;
    @JsonProperty("ware_house_id")
    @NotEmpty
    private String wareHouseId;
}
