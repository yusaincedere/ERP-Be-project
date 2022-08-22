package com.iknow.stocktrackingbe.payload.response.bom;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomResponse {

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
    @JsonProperty("product_id")
    private Long mainProductID;
    @JsonProperty("quantity")
    private BigDecimal mainProductQuantity;

    private BigDecimal efficiency;

    private String description;


    private Boolean approved;

    private Boolean draft;

    @JsonProperty("bom_details")
    private List<BomDetailResponse> bomDetailResponses;

    @JsonProperty("total_cost")
    private BigDecimal totalCost;
    @JsonProperty("unit_cost")
    private BigDecimal unitCost;

}
