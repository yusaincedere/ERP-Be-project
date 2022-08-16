package com.iknow.stocktrackingbe.controller;

import com.iknow.stocktrackingbe.model.Bom;
import com.iknow.stocktrackingbe.payload.request.BomRequest;
import com.iknow.stocktrackingbe.payload.request.IdListRequest;
import com.iknow.stocktrackingbe.payload.response.mapper.BomResponseMapper;
import com.iknow.stocktrackingbe.payload.response.BomResponse;
import com.iknow.stocktrackingbe.service.BomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bom")
public class BomController {
    private final BomService bomService;
    
    private final BomResponseMapper bomResponseMapper;




    @GetMapping("/bomList")
    public ResponseEntity<List<BomResponse>>getBomList(Pageable page){
        return ResponseEntity.ok(bomResponseMapper.mapper(bomService.getBomList(page)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BomResponse>>getBomList(@PathVariable String name, Pageable page){
        return ResponseEntity.ok(bomResponseMapper.mapper(bomService.getBomListByName(name,page)));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BomResponse>geBomById(@PathVariable String id){
        return ResponseEntity.ok(bomResponseMapper.mapper(bomService.getBomById(id)));
    }

    @GetMapping("/clone/{id}")
    public ResponseEntity<BomResponse>cloneBom(@PathVariable String id){
        return ResponseEntity.ok(bomResponseMapper.mapper(bomService.cloneBom(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<BomResponse> createBom(@Valid @RequestBody BomRequest bomRequest){
       return ResponseEntity.ok(bomResponseMapper.mapper(bomService.createBom(bomRequest)));

    }

    @PostMapping("/createDraft")
    public ResponseEntity<BomResponse> createDraft(@Valid @RequestBody BomRequest bomRequest){
        return ResponseEntity.ok(bomResponseMapper.mapper(bomService.createDraft(bomRequest)));
    }

    @PutMapping("/approve/{id}")
    public void approveBom(@PathVariable String id){
        bomService.approveBom(id);
    }

    @DeleteMapping(path = "/delete")
    public void deleteProducts(@RequestBody IdListRequest idList){
        bomService.deleteBom(idList.getIdList());
    }

    
}
