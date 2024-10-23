package com.simple.auth.banking.model.request;

import com.simple.auth.banking.constants.enums.AccountStatus;
import com.simple.auth.banking.constants.enums.AccountType;
import com.simple.auth.banking.constants.enums.CardStatus;
import com.simple.auth.banking.constants.enums.TransactStatus;

import java.math.BigDecimal;
import java.sql.Date;

public class AccountRequest {

    private Long accountNo;

    private AccountType accountType;

    private String name;

    private String customerId;

    private AccountStatus accountStatus;

    private TransactStatus transactStatus;

    private BigDecimal transactionLimit;

    private String cardNo;

    private CardStatus cardStatus;

    private Date cardExpiry;

    private String encryptedCardNo;
}
