package com.simple.auth.banking.model.request;

import com.simple.auth.banking.constants.enums.TransactionAction;
import com.simple.auth.banking.constants.enums.TransactionStatus;
import com.simple.auth.banking.constants.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransactionsRequestTest {
    @InjectMocks
    private TransactionsRequest transactionsRequest;

    @Test
    void transactionsRequestTest() {
        transactionsRequest = new TransactionsRequest();
        transactionsRequest.setAccountNo(1L);
        transactionsRequest.setBalance(BigDecimal.valueOf(100));
        transactionsRequest.setTransactionAction(TransactionAction.CREDIT);
        transactionsRequest.setTransactionType(TransactionType.ONLINE);
        transactionsRequest.setTransactionStatus(TransactionStatus.ACCEPTED);
        transactionsRequest.setDebitAccount(2L);
        transactionsRequest.setCreditAccount(3L);
        transactionsRequest.setDebitAmount(BigDecimal.valueOf(100));

        assertEquals(1L, transactionsRequest.getAccountNo());
        assertEquals(BigDecimal.valueOf(100), transactionsRequest.getBalance());
        assertEquals(TransactionAction.CREDIT, transactionsRequest.getTransactionAction());
        assertEquals(TransactionType.ONLINE, transactionsRequest.getTransactionType());
        assertEquals(TransactionStatus.ACCEPTED, transactionsRequest.getTransactionStatus());
        assertEquals(2L, transactionsRequest.getDebitAccount());
        assertEquals(3L, transactionsRequest.getCreditAccount());
        assertEquals(BigDecimal.valueOf(100), transactionsRequest.getDebitAmount());
    }
}
