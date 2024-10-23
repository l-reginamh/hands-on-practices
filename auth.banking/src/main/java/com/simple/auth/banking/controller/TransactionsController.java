package com.simple.auth.banking.controller;

import com.simple.auth.banking.model.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @GetMapping("/all/{accountId}")
    public ResponseEntity<ApiResponse> getAllTransactions(@PathVariable Long accountId) {
        return null;
    }

    @GetMapping("/get/{transactionId}")
    public ResponseEntity<ApiResponse> getTransaction(@PathVariable Long transactionId) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTransaction() {
        return null;
    }

    @PostMapping("/update/{transactionId}")
    public ResponseEntity<ApiResponse> updateTransaction(@PathVariable Long transactionId) {
        return null;
    }

}
