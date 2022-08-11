package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionProductListRequest{
    @JsonProperty("prescription_product_list")
    private List<PrescriptionProductRequest> prescriptionProductRequests;
}
