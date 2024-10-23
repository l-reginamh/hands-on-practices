package com.simple.auth.banking.controller;

import com.simple.auth.banking.model.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactController {

    @GetMapping("/{contactId}")
    public ResponseEntity<ApiResponse> getContact(@PathVariable String contactId) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createContact() {
        return null;
    }

    @PostMapping("/update/{contactId}")
    public ResponseEntity<ApiResponse> updateContact() {
        return null;
    }

}
