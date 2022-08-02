package com.iknow.stocktrackingbe.payload.request;

import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionProductRequest{
    private List<PrescriptionProduct> prescriptionProductList;
    private List<String> productIdList;
}
