package com.cibertec.ERP_Product.controller;

import com.cibertec.ERP_Product.DTO.LoginRequest;
import com.cibertec.ERP_Product.DTO.LoginResponse;
import com.cibertec.ERP_Product.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        return ResponseEntity.ok(authService.login(request));
    }
}
