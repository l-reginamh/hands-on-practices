package com.simple.auth.banking.model.dto;

import com.simple.auth.banking.constants.enums.TransactionAction;
import com.simple.auth.banking.constants.enums.TransactionStatus;
import com.simple.auth.banking.constants.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TransactionsDtoTest {
    @InjectMocks
    private TransactionsDto transactionsDto;

    @Test
    void transactionsDtoTest() {
        transactionsDto = new TransactionsDto();
        transactionsDto.setId(1L);
        transactionsDto.setAccountNo(10000001L);
        transactionsDto.setBalance(new BigDecimal("10.00"));
        transactionsDto.setTransactionAction(TransactionAction.DEBIT);
        transactionsDto.setTransactionType(TransactionType.ONLINE);
        transactionsDto.setTransactionStatus(TransactionStatus.CANCELLED);
        transactionsDto.setDebitAccount(10000001L);
        transactionsDto.setCreditAccount(10000002L);
        transactionsDto.setDebitAmount(new BigDecimal("10.00"));
        transactionsDto.setCreditAmount(new BigDecimal("0.00"));
        transactionsDto.setTransactionDate(new Date(Calendar.getInstance().getTimeInMillis()));

        assertEquals(1L, transactionsDto.getId());
        assertEquals(10000001L, transactionsDto.getAccountNo());
        assertEquals(new BigDecimal("10.00"), transactionsDto.getBalance());
        assertEquals(TransactionAction.DEBIT, transactionsDto.getTransactionAction());
        assertEquals(TransactionType.ONLINE, transactionsDto.getTransactionType());
        assertEquals(TransactionStatus.CANCELLED, transactionsDto.getTransactionStatus());
        assertEquals(10000001L, transactionsDto.getDebitAccount());
        assertEquals(10000002L, transactionsDto.getCreditAccount());
        assertEquals(new BigDecimal("10.00"), transactionsDto.getDebitAmount());
        assertEquals(new BigDecimal("0.00"), transactionsDto.getCreditAmount());
        assertNotNull(transactionsDto.getTransactionDate());
    }
}
