package com.simple.auth.banking.controller;

import com.simple.auth.banking.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ServiceUserController {
    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser() {
        return null;
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<ApiResponse> activateUser() {
        return null;
    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse> deactivateUser() {
        return null;
    }
}
