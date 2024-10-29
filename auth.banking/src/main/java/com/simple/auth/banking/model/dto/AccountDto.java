package com.simple.auth.banking.model.dto;

import com.simple.auth.banking.constants.enums.AccountStatus;
import com.simple.auth.banking.constants.enums.AccountType;
import com.simple.auth.banking.constants.enums.CardStatus;
import com.simple.auth.banking.constants.enums.TransactStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;

    private Long accountNo;

    private AccountType accountType;

    private String name;

    private String customerId;

    private AccountStatus accountStatus;

    private TransactStatus transactStatus;

    private BigDecimal transactionLimit;

    private String cardNo;

    private CardStatus cardStatus;

    private String cardExpiry;

    private String encryptedCardNo;
}
