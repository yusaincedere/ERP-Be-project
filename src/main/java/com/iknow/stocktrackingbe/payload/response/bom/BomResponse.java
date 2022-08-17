package com.iknow.stocktrackingbe.payload.response.bom;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BomResponse {

    private String id;
    @JsonProperty("bom_code")
    private String bomCode;
    @JsonProperty("bom_name")
    private String bomName;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonProperty("product_name")
    private String mainProductName;
    @JsonProperty("product_id")
    private String mainProductID;
    @JsonProperty("quantity")
    private BigDecimal mainProductQuantity;

    private BigDecimal efficiency;

    private String description;


    private Boolean approved;

    private Boolean draft;

    @JsonProperty("bom_details")
    private List<BomDetailResponse> bomDetailResponses;


}
