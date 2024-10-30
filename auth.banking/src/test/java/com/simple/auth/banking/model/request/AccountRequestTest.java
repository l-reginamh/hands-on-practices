package com.simple.auth.banking.model.request;

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
class AccountRequestTest {
    @InjectMocks
    private AccountRequest accountRequest;

    @Test
    void accountRequestTest() {
        accountRequest = new AccountRequest();

        accountRequest.setAccountNo(10000001L);
        accountRequest.setAccountType(AccountType.SAVINGS);
        accountRequest.setName("Jane Doe");
        accountRequest.setCustomerId("910101101001");
        accountRequest.setAccountStatus(AccountStatus.ACTIVE);
        accountRequest.setTransactStatus(TransactStatus.ACTIVE);
        accountRequest.setTransactionLimit(new BigDecimal("3000.00"));
        accountRequest.setCardNo("1234123412341234");
        accountRequest.setCardStatus(CardStatus.ACTIVE);
        accountRequest.setCardExpiry("01/28");
        accountRequest.setCardNo("************1234");

        assertEquals(10000001L, accountRequest.getAccountNo());
        assertEquals(AccountType.SAVINGS, accountRequest.getAccountType());
        assertEquals("Jane Doe", accountRequest.getName());
        assertEquals("910101101001", accountRequest.getCustomerId());
        assertEquals(AccountStatus.ACTIVE, accountRequest.getAccountStatus());
        assertEquals(TransactStatus.ACTIVE, accountRequest.getTransactStatus());
        assertEquals(new BigDecimal("3000.00"), accountRequest.getTransactionLimit());
        assertEquals(CardStatus.ACTIVE, accountRequest.getCardStatus());
        assertEquals("01/28", accountRequest.getCardExpiry());
        assertEquals("************1234", accountRequest.getCardNo());
    }
}
