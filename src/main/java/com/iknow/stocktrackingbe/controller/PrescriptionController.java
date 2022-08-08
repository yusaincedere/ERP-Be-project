package com.iknow.stocktrackingbe.controller;
import com.iknow.stocktrackingbe.model.Prescription;
import com.iknow.stocktrackingbe.model.mapper.PrescriptionResponseMapper;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.response.PrescriptionResponse;
import com.iknow.stocktrackingbe.payload.request.PrescriptionProductListRequest;
import com.iknow.stocktrackingbe.payload.request.PrescriptionRequest;
import com.iknow.stocktrackingbe.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final PrescriptionResponseMapper prescriptionResponseMapper;



    @GetMapping(path = "/id/{id}")
    public ResponseEntity<PrescriptionResponse> getPrescriptionById(@PathVariable(required = false) String id){
          return ResponseEntity.ok(prescriptionResponseMapper.mapper(prescriptionService.getPrescriptionById(id)));
    }
    @GetMapping("/prescriptions")
    public ResponseEntity<List<PrescriptionResponse>> getPrescriptions(@PageableDefault (sort = "created",direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.ok(prescriptionResponseMapper.mapper(prescriptionService.getPrescriptions(page)));
    }

    @GetMapping(path = "/{id}/clone")
    public ResponseEntity<PrescriptionResponse> clonePrescription(@PathVariable String id){
            return ResponseEntity.ok(prescriptionResponseMapper.mapper(prescriptionService.clonePrescription(id)));
    }
    @GetMapping(path = "/version/{version}")
    public ResponseEntity<List<PrescriptionResponse>> searchByVersion(@PathVariable String version,Pageable pageable){
        return ResponseEntity.ok(prescriptionResponseMapper.mapper(prescriptionService.searchByVersion(version,pageable)));
    }

    @PostMapping
    public void createPrescription(@Valid @RequestBody PrescriptionRequest prescriptionRequest){
        prescriptionService.createNewPrescription(prescriptionRequest);
    }
    @PostMapping("/draft")
    public void createDraftPrescription(@Valid  @RequestBody PrescriptionRequest prescriptionRequest){
        prescriptionService.createDraftPrescription(prescriptionRequest);
    }

    @PutMapping("/{id}/approve")
    public void approvePrescription(@Valid @PathVariable String id){
        prescriptionService.approvePrescription(id);
    }


    @PutMapping("/{id}/update")
    public void updatePrescription(
            @Valid
            @PathVariable String id,
            @RequestBody Prescription prescription){
        prescriptionService.updatePrescription(id,prescription);
    }
    @PutMapping("/{id}/add")
    public void addProductsToPrescription(
            @Valid
            @PathVariable String id,
            @RequestBody PrescriptionProductListRequest prescriptionProductListRequest){
        prescriptionService.addProductsToPrescription(id,prescriptionProductListRequest);
    }
    @DeleteMapping(path = "/delete")
    public void deletePrescriptions(@RequestBody IdListRequest ids) {
        prescriptionService.deletePrescriptions(ids.getIdList());
    }
}