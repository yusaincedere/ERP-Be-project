package com.iknow.stocktrackingbe.payload.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

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
    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("expiry_date")
    private LocalDate expiryDate;
    @JsonProperty("produce_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate produceDate;
    @JsonProperty("amount_of_usage")
    private String amountOfUsage;
    @JsonProperty("prospectus_id")
    private String prospectusId;
    @JsonProperty("stock_cards")
    private List<StockCardResponseProduct> stockCards;
    @JsonProperty("product_ingredients")
    private List<ProductIngredientResponseProduct> productIngredients;

}
