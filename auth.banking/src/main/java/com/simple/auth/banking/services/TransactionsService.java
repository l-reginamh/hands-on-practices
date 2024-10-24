package com.simple.auth.banking.services;

import com.simple.auth.banking.model.entity.Transactions;
import com.simple.auth.banking.model.request.TransactionsRequest;

import java.util.List;
import java.util.Optional;

public interface TransactionsService {
    List<Transactions> getTransactionsByAccountNo(Long accountNo);

    Transactions getTransactionById(Long id);

    Transactions createTransaction(TransactionsRequest transactionsRequest);

    Transactions updateTransaction(Long id, TransactionsRequest transactionsRequest);
}
