package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.enums.*;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.dto.TransactionsDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.entity.Transactions;
import com.simple.auth.banking.model.request.TransactionsRequest;
import com.simple.auth.banking.repository.TransactionsRepository;
import com.simple.auth.banking.services.AccountService;
import com.simple.auth.banking.utils.mappers.TransactionsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        boolean result = transactionService.recipientAccountCheck(1L);
        assertTrue(result);
    }

    @Test
    void recipientAccountCheck_resultFalse() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(false);
        boolean result = transactionService.recipientAccountCheck(1L);
        assertFalse(result);
    }

    @Test
    void checkTransactStatus_resultTrue() {
        assertTrue(transactionService.checkTransactStatus(TransactStatus.ACTIVE));
    }

    @Test
    void checkTransactStatus_resultFalse() {
        assertFalse(transactionService.checkTransactStatus(TransactStatus.LOCKED));
    }

    @Test
    void checkTransactionLimit_resultTrue() {
        assertTrue(transactionService.checkTransactionLimit(new BigDecimal("100.00"), new BigDecimal("10.00")));
    }

    @Test
    void checkTransactionLimit_resultFalse() {
        assertFalse(transactionService.checkTransactionLimit(new BigDecimal("100.00"), new BigDecimal("1000.00")));
    }

    @Test
    void checkBalance_resultTrue() {
        assertTrue(transactionService.checkBalance(new BigDecimal("100.00"), new BigDecimal("10.00")));
    }

    @Test
    void checkBalance_resultFalse() {
        assertFalse(transactionService.checkBalance(new BigDecimal("100.00"), new BigDecimal("1000.00")));
    }

    @Test
    void getTransactionsByAccountNo() {
        when(transactionsRepository.findAllByAccountNo(anyLong())).thenReturn(Arrays.asList(mockTransaction(), mockTransaction()));
        List<Transactions> result = transactionService.getTransactionsByAccountNo(1L);
        assertNotNull(result);
    }

    @Test
    void getTransactionById_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> transactionService.getTransactionById(1L));
        assertEquals(MessageConstants.TRANSACTION_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getTransactionById_resultSuccess() {
        when(transactionsRepository.findById(anyLong())).thenReturn(Optional.of(mockTransaction()));
        Transactions result = transactionService.getTransactionById(1L);
        assertNotNull(result);
    }

    @Test
    void createTransaction_resultDataNotFoundException() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(false);
        Exception exception = assertThrows(DataNotFoundException.class, () -> transactionService.createTransaction(mockTransactionRequest()));
        assertEquals(MessageConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void createTransaction_resultInvalidRequestException_invalidTransactionStatus() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        Account account = mockAccount();
        account.setTransactStatus(TransactStatus.LOCKED);
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(account);
        Exception exception = assertThrows(InvalidRequestException.class, () -> transactionService.createTransaction(mockTransactionRequest()));
        assertEquals(MessageConstants.TRANSACTION_INVALID_TRANSACT, exception.getMessage());
    }

    @Test
    void createTransaction_resultInvalidRequestException_exceedTransactionLimit() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(mockAccount());
        TransactionsRequest transactionsRequest = mockTransactionRequest();
        transactionsRequest.setDebitAmount(BigDecimal.valueOf(1000));
        Exception exception = assertThrows(InvalidRequestException.class, () -> transactionService.createTransaction(transactionsRequest));
        assertEquals(MessageConstants.TRANSACTION_EXCEED_LIMIT, exception.getMessage());
    }

    @Test
    void createTransaction_resultInvalidRequestException_insufficientBalance() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        Account account = mockAccount();
        account.setTransactionLimit(BigDecimal.valueOf(10000));
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(account);
        TransactionsRequest transactionsRequest = mockTransactionRequest();
        transactionsRequest.setDebitAmount(BigDecimal.valueOf(1000));
        transactionsRequest.setBalance(new BigDecimal("100.00"));
        Exception exception = assertThrows(InvalidRequestException.class, () -> transactionService.createTransaction(transactionsRequest));
        assertEquals(MessageConstants.TRANSACTION_INSUFFICIENT_BALANCE, exception.getMessage());
    }

    @Test
    void createTransaction_resultSuccess() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        Account account = mockAccount();
        account.setTransactionLimit(BigDecimal.valueOf(10000));
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(account);
        TransactionsRequest transactionsRequest = mockTransactionRequest();
        transactionsRequest.setDebitAmount(BigDecimal.valueOf(1000));
        transactionsRequest.setBalance(new BigDecimal("9000.00"));
        Transactions transactions = mockTransaction();
        transactions.setBalance(new BigDecimal("9000.00"));
        when(transactionsRepository.findFirstByAccountNoOrderByTransactionDateDesc(anyLong())).thenReturn(Optional.of(transactions));
        when(transactionsRepository.save(any(Transactions.class))).thenReturn(mockTransaction());
        Transactions result = transactionService.createTransaction(transactionsRequest);
        assertNotNull(result);
    }

    @Test
    void updateTransaction_resultDataNotFoundException_accountNotFound() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(false);
        Exception exception = assertThrows(DataNotFoundException.class, () -> transactionService.updateTransaction(1L, mockTransactionRequest()));
        assertEquals(MessageConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateTransaction_resultInvalidRequestException_invalidTransactionStatus() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        Account account = mockAccount();
        account.setTransactStatus(TransactStatus.LOCKED);
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(account);
        Exception exception = assertThrows(InvalidRequestException.class, () -> transactionService.updateTransaction(1L, mockTransactionRequest()));
        assertEquals(MessageConstants.TRANSACTION_INVALID_TRANSACT, exception.getMessage());
    }

    @Test
    void updateTransaction_resultInvalidRequestException_exceedTransactionLimit() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(mockAccount());
        TransactionsRequest transactionsRequest = mockTransactionRequest();
        transactionsRequest.setDebitAmount(BigDecimal.valueOf(1000));
        Exception exception = assertThrows(InvalidRequestException.class, () -> transactionService.updateTransaction(1L, transactionsRequest));
        assertEquals(MessageConstants.TRANSACTION_EXCEED_LIMIT, exception.getMessage());
    }

    @Test
    void updateTransaction_resultInvalidRequestException_insufficientBalance() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        Account account = mockAccount();
        account.setTransactionLimit(BigDecimal.valueOf(10000));
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(account);
        TransactionsRequest transactionsRequest = mockTransactionRequest();
        transactionsRequest.setDebitAmount(BigDecimal.valueOf(1000));
        Exception exception = assertThrows(InvalidRequestException.class, () -> transactionService.updateTransaction(1L, transactionsRequest));
        assertEquals(MessageConstants.TRANSACTION_INSUFFICIENT_BALANCE, exception.getMessage());
    }

    @Test
    void updateTransaction_resultDataNotFoundException_transactionNotFound() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        Account account = mockAccount();
        account.setTransactionLimit(BigDecimal.valueOf(10000));
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(account);
        TransactionsRequest transactionsRequest = mockTransactionRequest();
        transactionsRequest.setDebitAmount(BigDecimal.valueOf(1000));
        transactionsRequest.setBalance(new BigDecimal("9000.00"));
        Transactions transactions = mockTransaction();
        transactions.setBalance(new BigDecimal("9000.00"));
        when(transactionsRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(DataNotFoundException.class, () -> transactionService.updateTransaction(1L, transactionsRequest));
        assertEquals(MessageConstants.TRANSACTION_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateTransaction_resultSuccess() {
        when(accountService.existsAccountByAccountNo(anyLong())).thenReturn(true);
        Account account = mockAccount();
        account.setTransactionLimit(BigDecimal.valueOf(10000));
        when(accountService.getAccountByAccountNo(anyLong())).thenReturn(account);
        TransactionsRequest transactionsRequest = mockTransactionRequest();
        transactionsRequest.setDebitAmount(BigDecimal.valueOf(1000));
        transactionsRequest.setBalance(new BigDecimal("9000.00"));
        Transactions transactions = mockTransaction();
        transactions.setBalance(new BigDecimal("9000.00"));
        when(transactionsRepository.findById(anyLong())).thenReturn(Optional.of(mockTransaction()));
        when(transactionsRepository.save(any(Transactions.class))).thenReturn(mockTransaction());
        Transactions result = transactionService.updateTransaction(1L, transactionsRequest);
        assertNotNull(result);
    }

    @Test
    void convertToDto() {
        when(transactionsMapper.convertToDto(any())).thenReturn(mockTransactionDto());
        TransactionsDto result = transactionService.convertToDto(mockTransaction());
        assertNotNull(result);
    }

    @Test
    void convertToEntity() {
        when(transactionsMapper.convertToEntity(any())).thenReturn(mockTransaction());
        Transactions result = transactionService.convertToEntity(mockTransactionDto());
        assertNotNull(result);
    }

    private Account mockAccount() {
        Account account = new Account();
        account.setAccountNo(100000001L);
        account.setCustomerId("990101101001");
        account.setAccountType(AccountType.SAVINGS);
        account.setCardNo("1234567890123456");
        account.setCardExpiry("01/22");
        account.setEncryptedCardNo("1234567890123456");
        account.setTransactionLimit(BigDecimal.valueOf(100));
        return account;
    }

    private Transactions mockTransaction() {
        Transactions transaction = new Transactions();
        transaction.setId(1L);
        transaction.setAccountNo(10000001L);
        transaction.setBalance(new BigDecimal("10.00"));
        transaction.setTransactionAction(TransactionAction.DEBIT);
        transaction.setTransactionType(TransactionType.ONLINE);
        transaction.setTransactionStatus(TransactionStatus.ACCEPTED);
        transaction.setDebitAccount(10000001L);
        transaction.setCreditAccount(10000002L);
        transaction.setDebitAmount(new BigDecimal("10.00"));
        transaction.setCreditAmount(new BigDecimal("0.00"));
        transaction.setTransactionDate(new Date(Calendar.getInstance().getTimeInMillis()));
        return transaction;
    }

    private TransactionsRequest mockTransactionRequest() {
        TransactionsRequest transactionRequest = new TransactionsRequest();
        transactionRequest.setAccountNo(10000001L);
        transactionRequest.setDebitAccount(10000001L);
        transactionRequest.setCreditAccount(10000002L);
        transactionRequest.setDebitAmount(new BigDecimal("10.00"));
        transactionRequest.setCreditAmount(new BigDecimal("0.00"));
        transactionRequest.setTransactionType(TransactionType.ONLINE);
        transactionRequest.setTransactionAction(TransactionAction.DEBIT);
        transactionRequest.setBalance(new BigDecimal("10.00"));
        transactionRequest.setTransactionStatus(TransactionStatus.ACCEPTED);
        return transactionRequest;
    }

    private TransactionsDto mockTransactionDto() {
        TransactionsDto transactionDto = new TransactionsDto();
        transactionDto.setId(1L);
        transactionDto.setAccountNo(10000001L);
        transactionDto.setBalance(new BigDecimal("10.00"));
        transactionDto.setTransactionAction(TransactionAction.DEBIT);
        transactionDto.setTransactionType(TransactionType.ONLINE);
        transactionDto.setTransactionStatus(TransactionStatus.ACCEPTED);
        transactionDto.setDebitAccount(10000001L);
        transactionDto.setCreditAccount(10000002L);
        transactionDto.setDebitAmount(new BigDecimal("10.00"));
        transactionDto.setCreditAmount(new BigDecimal("0.00"));
        transactionDto.setTransactionDate(new Date(Calendar.getInstance().getTimeInMillis()));
        return transactionDto;
    }
}
