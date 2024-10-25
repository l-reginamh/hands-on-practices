package com.simple.auth.banking.controller;

import com.simple.auth.banking.model.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @GetMapping("/{accountNo}/all")
    public ResponseEntity<ApiResponse> getAllTransactions(@PathVariable Long accountNo) {
        return null;
    }

    @GetMapping("/{id}/transaction")
    public ResponseEntity<ApiResponse> getTransaction(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTransaction() {
        return null;
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<ApiResponse> updateTransaction(@PathVariable Long transactionId) {
        return null;
    }

}
