package com.simple.auth.banking.model.entity;

import com.simple.auth.banking.constants.enums.AccountStatus;
import com.simple.auth.banking.constants.enums.AccountType;
import com.simple.auth.banking.constants.enums.CardStatus;
import com.simple.auth.banking.constants.enums.TransactStatus;
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
class AccountTest {
    @InjectMocks
    private Account account;

    @Test
    void accountTest() {
        account.setId(1L);
        account.setAccountNo(10000001L);
        account.setAccountType(AccountType.SAVINGS);
        account.setName("Jane Doe");
        account.setCustomerId("910101101001");
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setTransactStatus(TransactStatus.ACTIVE);
        account.setTransactionLimit(new BigDecimal("3000.00"));
        account.setCardNo("1234123412341234");
        account.setCardStatus(CardStatus.ACTIVE);
        account.setCardExpiry("01/28");
        account.setEncryptedCardNo("************1234");
        account.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
        account.setModifiedDate(new Date(Calendar.getInstance().getTimeInMillis()));

        assertEquals(1L, account.getId());
        assertEquals(10000001L, account.getAccountNo());
        assertEquals(AccountType.SAVINGS, account.getAccountType());
        assertEquals("Jane Doe", account.getName());
        assertEquals("910101101001", account.getCustomerId());
        assertEquals(AccountStatus.ACTIVE, account.getAccountStatus());
        assertEquals(TransactStatus.ACTIVE, account.getTransactStatus());
        assertEquals(new BigDecimal("3000.00"), account.getTransactionLimit());
        assertEquals("1234123412341234", account.getCardNo());
        assertEquals(CardStatus.ACTIVE, account.getCardStatus());
        assertEquals("01/28", account.getCardExpiry());
        assertEquals("************1234", account.getEncryptedCardNo());
        assertNotNull(account.getCreatedDate());
        assertNotNull(account.getModifiedDate());
    }
}
