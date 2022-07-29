package com.iknow.stocktrackingbe.payload.request;

import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionProductRequest{
    private List<PrescriptionProduct> prescriptionProductList;
    private List<String> productIdList;
}
