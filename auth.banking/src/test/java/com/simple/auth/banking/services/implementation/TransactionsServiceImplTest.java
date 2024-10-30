package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.repository.TransactionsRepository;
import com.simple.auth.banking.services.AccountService;
import com.simple.auth.banking.utils.mappers.TransactionsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionsServiceImplTest {
    @InjectMocks
    private TransactionsServiceImpl transactionService;

    @Mock
    private TransactionsRepository transactionsRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionsMapper transactionsMapper;

    @Test
    void recipientAccountCheck_resultTrue() {

    }

    @Test
    void recipientAccountCheck_resultFalse() {

    }

    @Test
    void checkTransactStatus_resultTrue() {

    }

    @Test
    void checkTransactStatus_resultFalse() {

    }

    @Test
    void checkTransactionLimit_resultTrue() {

    }

    @Test
    void checkTransactionLimit_resultFalse() {

    }

    @Test
    void checkBalance_resultTrue() {

    }

    @Test
    void checkBalance_resultFalse() {

    }

    @Test
    void getTransactionsByAccountNo() {

    }

    @Test
    void getTransactionById_resultDataNotFoundException() {

    }

    @Test
    void getTransactionById_resultSuccess() {

    }

    @Test
    void createTransaction_resultDataNotFoundException() {

    }

    @Test
    void createTransaction_resultInvalidRequestException_invalidTransactionStatus() {

    }

    @Test
    void createTransaction_resultInvalidRequestException_exceedTransactionLimit() {

    }

    @Test
    void createTransaction_resultInvalidRequestException_insufficientBalance() {

    }

    @Test
    void createTransaction_resultInvalidRequestException_invalidTransaction() {

    }

    @Test
    void createTransaction_resultSuccess() {

    }

    @Test
    void updateTransaction_resultDataNotFoundException_accountNotFound() {

    }

    @Test
    void updateTransaction_resultInvalidRequestException_invalidTransactionStatus() {

    }

    @Test
    void updateTransaction_resultInvalidRequestException_exceedTransactionLimit() {

    }

    @Test
    void updateTransaction_resultInvalidRequestException_insufficientBalance() {

    }

    @Test
    void updateTransaction_resultDataNotFoundException_transactionNotFound() {

    }

    @Test
    void updateTransaction_resultSuccess() {

    }

    @Test
    void convertToDto() {

    }

    @Test
    void convertToEntity() {

    }
}
