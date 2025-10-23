package com.hms.controller;

import com.hms.dto.LoginRequest;
import com.hms.dto.LoginResponse;
import com.hms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authService.authenticate(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                LoginResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build()
            );
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken() {
        // This would normally verify JWT token
        // For demo purposes, return a simple response
        return ResponseEntity.ok().body("{\"status\": \"valid\"}");
    }
}