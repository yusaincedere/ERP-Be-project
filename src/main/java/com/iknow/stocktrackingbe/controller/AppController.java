package com.iknow.stocktrackingbe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping
    public ResponseEntity<?> appHealth(){
        return ResponseEntity.ok("App is running");
    }
}
