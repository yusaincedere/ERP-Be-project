package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
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
