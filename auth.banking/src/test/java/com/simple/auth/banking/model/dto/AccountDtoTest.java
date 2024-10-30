package com.simple.auth.banking.model.dto;

import com.simple.auth.banking.constants.enums.AccountStatus;
import com.simple.auth.banking.constants.enums.AccountType;
import com.simple.auth.banking.constants.enums.CardStatus;
import com.simple.auth.banking.constants.enums.TransactStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccountDtoTest {
    @InjectMocks
    private AccountDto accountDto;

    @Test
    void accountDtoTest() {
        accountDto.setId(1L);
        accountDto.setAccountNo(10000001L);
        accountDto.setAccountType(AccountType.SAVINGS);
        accountDto.setName("Jane Doe");
        accountDto.setCustomerId("910101101001");
        accountDto.setAccountStatus(AccountStatus.ACTIVE);
        accountDto.setTransactStatus(TransactStatus.ACTIVE);
        accountDto.setTransactionLimit(new BigDecimal("3000.00"));
        accountDto.setCardNo("1234123412341234");
        accountDto.setCardStatus(CardStatus.ACTIVE);
        accountDto.setCardExpiry("01/28");
        accountDto.setEncryptedCardNo("************1234");

        assertEquals(1L, accountDto.getId());
        assertEquals(10000001L, accountDto.getAccountNo());
        assertEquals(AccountType.SAVINGS, accountDto.getAccountType());
        assertEquals("Jane Doe", accountDto.getName());
        assertEquals("910101101001", accountDto.getCustomerId());
        assertEquals(AccountStatus.ACTIVE, accountDto.getAccountStatus());
        assertEquals(TransactStatus.ACTIVE, accountDto.getTransactStatus());
        assertEquals(new BigDecimal("3000.00"), accountDto.getTransactionLimit());
        assertEquals("1234123412341234", accountDto.getCardNo());
        assertEquals(CardStatus.ACTIVE, accountDto.getCardStatus());
        assertEquals("01/28", accountDto.getCardExpiry());
        assertEquals("************1234", accountDto.getEncryptedCardNo());
    }
}
