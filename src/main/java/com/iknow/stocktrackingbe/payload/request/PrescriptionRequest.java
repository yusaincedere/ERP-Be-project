package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionRequest {
    @JsonProperty("prescription_version")
    private String prescriptionVersion;
    @NotEmpty
    @JsonProperty("wareHouse_id")
    private String wareHouseId;
    @JsonProperty("start_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonProperty("end_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    @JsonProperty("prescription_product_list")
    private List<PrescriptionProductRequest> prescriptionProductRequests;

}
