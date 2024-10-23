package com.simple.auth.banking.controller;

import com.simple.auth.banking.model.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{accountId}")
    public ResponseEntity<ApiResponse> getAccount(@PathVariable Long accountId) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAccount() {
        return null;
    }

    @PostMapping("/update/{accountId}")
    public ResponseEntity<ApiResponse> createAccount(@PathVariable Long accountId) {
        return null;
    }

}
