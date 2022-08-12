package com.iknow.stocktrackingbe.payload.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    @JsonProperty("product_code")
    private String productCode;
    @JsonProperty("name")
    private String productName;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("product_unit")
    private String productUnit;



}