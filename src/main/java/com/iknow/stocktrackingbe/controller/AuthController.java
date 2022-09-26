package com.iknow.stocktrackingbe.controller;


import com.iknow.stocktrackingbe.payload.request.LoginRequest;
import com.iknow.stocktrackingbe.payload.request.RefreshTokenRequest;
import com.iknow.stocktrackingbe.payload.request.UserRegisterRequest;
import com.iknow.stocktrackingbe.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok().body(authService.registerUser(userRegisterRequest));
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok().body(authService.refreshTokens(refreshTokenRequest.getToken()));
    }




}
