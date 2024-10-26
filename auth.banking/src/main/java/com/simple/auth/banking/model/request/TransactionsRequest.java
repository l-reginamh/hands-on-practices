package com.simple.auth.banking.model.request;

import com.simple.auth.banking.constants.enums.TransactionAction;
import com.simple.auth.banking.constants.enums.TransactionStatus;
import com.simple.auth.banking.constants.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionsRequest {

    private Long accountNo;

    private BigDecimal balance;

    private TransactionAction transactionAction;

    private TransactionType transactionType;

    private TransactionStatus transactionStatus;

    private Long debitAccount;

    private Long creditAccount;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;
}
