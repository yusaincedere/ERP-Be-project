package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Prospectus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotNull
    private BigDecimal price;
    @NotNull
    @JsonProperty("expiry_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate expiryDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("produce_date")
    private LocalDate produceDate;
    @NotNull
    @JsonProperty("name")
    private String productName;
    @JsonProperty("product_code")
    private String productCode;
    @JsonProperty("amount_of_usage")
    private String amountOfUsage;
    @OneToOne
    private Prospectus prospectus;
    @JsonProperty("ingredient_ids")
    private List<String> ingredientIds;

}
