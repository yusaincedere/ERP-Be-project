package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
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
public class PrescriptionRequest {
    @JsonProperty("prescription_version")
    private String prescriptionVersion;
    @JsonProperty("wareHouse_id")
    private String wareHouseId;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonProperty("prescription_product_list")
    private List<PrescriptionProductRequest> prescriptionProductRequests;

}
