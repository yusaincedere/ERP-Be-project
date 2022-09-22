package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.payload.request.bom.BomDetailRequest;
import com.iknow.stocktrackingbe.payload.request.bom.BomRequest;
import com.iknow.stocktrackingbe.payload.response.bom.BomListResponse;
import com.iknow.stocktrackingbe.payload.response.mapper.BomListResponseMapper;
import com.iknow.stocktrackingbe.payload.response.mapper.BomResponseMapper;
import com.iknow.stocktrackingbe.payload.response.bom.BomResponse;
import com.iknow.stocktrackingbe.service.BomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bom")
public class BomController {
    private final BomService bomService;
    
    private final BomResponseMapper bomResponseMapper;

    private final BomListResponseMapper bomListResponseMapper;




    @GetMapping("/boms")
    public ResponseEntity<List<BomListResponse>>getBomList(Pageable page){
        return ResponseEntity.ok(bomListResponseMapper.mapper(bomService.getBomList(page)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BomListResponse>>getBomList(@PathVariable String name, Pageable page){
        return ResponseEntity.ok(bomListResponseMapper.mapper(bomService.getBomListByName(name,page)));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BomResponse>getBomById(@PathVariable Long id){
        return ResponseEntity.ok(bomResponseMapper.mapper(bomService.getBomById(id)));
    }

    @GetMapping("/clone/{id}")
    public ResponseEntity<BomResponse>cloneBom(@PathVariable Long id){
        return ResponseEntity.ok(bomResponseMapper.mapper(bomService.cloneBom(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<BomResponse> createBom(@RequestBody BomRequest bomRequest){
       return ResponseEntity.ok(bomResponseMapper.mapper(bomService.createBom(bomRequest)));

    }

    @PostMapping("/createDraft")
    public ResponseEntity<BomResponse> createDraft(@RequestBody BomRequest bomRequest){
        return ResponseEntity.ok(bomResponseMapper.mapper(bomService.createDraft(bomRequest)));
    }

    @PutMapping("/approve/{id}")
    public void approveBom(@PathVariable Long id){
        bomService.approveBom(id);
    }

    @PutMapping("/addBomDetail/{id}")
    public void addBomDetail(@PathVariable Long id, @RequestBody BomDetailRequest bomDetailRequest){
        bomService.addBomDetail(id,bomDetailRequest);
    }

    @PutMapping("/{id}/update")
    public void updateBom(
            @PathVariable Long id,
            @Valid @RequestBody BomRequest bomRequest){
        bomService.updateBom(id,bomRequest);
    }

    @PutMapping("/{id}/updateDetail")
    public void updateBomDetail(
            @PathVariable Long id,
            @Valid @RequestBody BomDetailRequest bomDetailRequest){
        bomService.updateBomDetail(id,bomDetailRequest);
    }

    @DeleteMapping(path = "/delete")
    public void deleteBom(@RequestParam(name = "ids") Set<Long> idList){
        bomService.deleteBom(idList);
    }
}
