package com.iknow.stocktrackingbe.payload.response;

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
public class BomResponse {

    @JsonProperty("bom_code")
    private String bomCode;
    @JsonProperty("bom_name")
    private String bomName;
    @JsonProperty("bom_name")
    private LocalDate startDate;
    @JsonProperty("bom_name")
    private LocalDate endDate;

    private boolean approved;

    private boolean draft;


}
