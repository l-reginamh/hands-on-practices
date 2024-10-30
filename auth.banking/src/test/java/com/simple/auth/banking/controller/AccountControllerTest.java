package com.simple.auth.banking.controller;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.response.ApiResponse;
import com.simple.auth.banking.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Test
    void getAllAccounts_resultDataFoundException() {
        when(accountService.getAccountsByCustomerId(anyString())).thenThrow(new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));

        ResponseEntity<ApiResponse> responseEntity = accountController.getAllAccounts("customerId");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void getAllAccounts_resultException() {
        when(accountService.getAccountsByCustomerId(anyString())).thenThrow(new InvalidRequestException(""));

        ResponseEntity<ApiResponse> responseEntity = accountController.getAllAccounts("customerId");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void getAllAccounts_resultSuccess() {
        ResponseEntity<ApiResponse> responseEntity = accountController.getAllAccounts("customerId");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAccount_resultDataFoundException() {
        when(accountService.getAccountById(anyLong())).thenThrow(new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));

        ResponseEntity<ApiResponse> responseEntity = accountController.getAccount(1L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void getAccount_resultException() {
        when(accountService.getAccountById(anyLong())).thenThrow(new InvalidRequestException(""));

        ResponseEntity<ApiResponse> responseEntity = accountController.getAccount(1L);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void getAccount_resultSuccess() {
        ResponseEntity<ApiResponse> responseEntity = accountController.getAccount(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void createAccount_resultAlreadyExistsException() {
        
    }

    @Test
    void createAccount_resultException() {
        
    }

    @Test
    void createAccount_resultSuccess() {
        
    }

    @Test
    void updateAccount_resultDataNotFoundException() {
        
    }

    @Test
    void updateAccount_resultException() {
        
    }

    @Test
    void updateAccount_resultSuccess() {
        
    }

    @Test
    void activateAccount_resultDataNotFoundException() {
        
    }

    @Test
    void activateAccount_resultException() {
        
    }

    @Test
    void activateAccount_resultSuccess() {
        
    }

    @Test
    void deactivateAccount_resultDataNotFoundException() {
        
    }

    @Test
    void deactivateAccount_resultException() {
        
    }

    @Test
    void deactivateAccount_resultSuccess() {
        
    }
}
