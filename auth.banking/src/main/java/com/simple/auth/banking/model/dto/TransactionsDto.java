package com.simple.auth.banking.model.dto;

import com.simple.auth.banking.constants.enums.TransactionAction;
import com.simple.auth.banking.constants.enums.TransactionType;
import com.simple.auth.banking.model.entity.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class TransactionsDto {
    private Long id;

    private Long accountNo;

    private BigDecimal balance;

    private TransactionAction transactionAction;

    private TransactionType transactionType;

    private String debitAccount;

    private String creditAccount;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private Date transactionDate;
}
