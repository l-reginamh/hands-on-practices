package com.simple.auth.banking.services;

import com.simple.auth.banking.constants.enums.TransactStatus;
import com.simple.auth.banking.model.dto.TransactionsDto;
import com.simple.auth.banking.model.entity.Transactions;
import com.simple.auth.banking.model.request.TransactionsRequest;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionsService {
    boolean recipientAccountCheck(Long accountNo);
    boolean checkTransactStatus(TransactStatus transactStatus);
    boolean checkTransactionLimit(BigDecimal transactionLimit, BigDecimal debitValue);
    boolean checkBalance(BigDecimal balance, BigDecimal debitValue);
    List<Transactions> getTransactionsByAccountNo(Long accountNo);
    Transactions getTransactionById(Long id);
    Transactions createTransaction(TransactionsRequest transactionsRequest);
    Transactions updateTransaction(Long id, TransactionsRequest transactionsRequest);
    TransactionsDto convertToDto(Transactions transactions);
    Transactions convertToEntity(TransactionsDto transactionsDto);
}
