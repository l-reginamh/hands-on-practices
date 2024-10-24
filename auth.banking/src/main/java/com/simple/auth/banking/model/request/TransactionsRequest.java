package com.simple.auth.banking.model.request;

import com.simple.auth.banking.constants.enums.TransactionAction;
import com.simple.auth.banking.constants.enums.TransactionType;

import java.math.BigDecimal;
import java.sql.Date;

public class TransactionsRequest {

    private Long accountNo;

    private BigDecimal balance;

    private TransactionAction transactionAction;

    private TransactionType transactionType;

    private String debitAccount;

    private String creditAccount;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;
}
