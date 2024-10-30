package com.simple.auth.banking.controller;

import com.simple.auth.banking.services.TransactionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionsControllerTest {
    @InjectMocks
    private TransactionsController transactionsController;

    @Mock
    private TransactionsService transactionsService;

    @Test
    void getAllTransactions_resultDataFoundException() {
        
    }

    @Test
    void getAllTransactions_resultException() {
        
    }

    @Test
    void getAllTransactions_resultSuccess() {
        
    }

    @Test
    void getTransaction_resultDataFoundException() {
        
    }

    @Test
    void getTransaction_resultException() {
        
    }

    @Test
    void getTransaction_resultSuccess() {
        
    }

    @Test
    void createTransaction_resultAlreadyExistsException() {
        
    }

    @Test
    void createTransaction_resultException() {
        
    }

    @Test
    void createTransaction_resultSuccess() {
        
    }

    @Test
    void updateTransaction_resultDataNotFoundException() {
        
    }

    @Test
    void updateTransaction_resultException() {
        
    }

    @Test
    void updateTransaction_resultSuccess() {
        
    }
}
