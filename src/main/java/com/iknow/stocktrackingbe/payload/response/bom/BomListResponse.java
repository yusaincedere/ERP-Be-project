package com.iknow.stocktrackingbe.payload.response.bom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomListResponse {
    private Long id;
    @JsonProperty("bom_code")
    private String bomCode;
    @JsonProperty("bom_name")
    private String bomName;
    @JsonProperty("start_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonProperty("end_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    @JsonProperty("product_name")
    private String mainProductName;
    @JsonProperty("quantity")
    private BigDecimal mainProductQuantity;
    private Boolean draft;
}
