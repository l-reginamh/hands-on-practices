package com.simple.auth.banking.controller;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.StatusFlags;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.request.AccountRequest;
import com.simple.auth.banking.model.response.ApiResponse;
import com.simple.auth.banking.model.response.StatusResponse;
import com.simple.auth.banking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAccount(@PathVariable Long id) {
        try {
            Account account = accountService.getAccountById(id);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), accountService.convertToDto(account)));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.USER_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        try {
            Account account = accountService.createAccount(accountRequest);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), accountService.convertToDto(account)));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        try {
            Account account = accountService.updateAccount(id, accountRequest);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), accountService.convertToDto(account)));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.USER_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

}
