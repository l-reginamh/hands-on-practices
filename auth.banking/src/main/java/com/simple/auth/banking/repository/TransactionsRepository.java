package com.simple.auth.banking.repository;


import com.simple.auth.banking.constants.enums.TransactionAction;
import com.simple.auth.banking.constants.enums.TransactionType;
import com.simple.auth.banking.model.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findAllByAccountNo(Long accountNo);
    List<Transactions> findAllByAccountNoAndTransactionAction(Long accountNo, TransactionAction transactionAction);
    List<Transactions> findAllByAccountNoAndTransactionType(Long accountNo, TransactionType transactionType);
    List<Transactions> findAllByAccountNoAndTransactionActionAndTransactionType(Long accountNo, TransactionAction transactionAction, TransactionType transactionType);
    Long countByAccountNoAndTransactionActionAndTransactionType(Long accountNo, TransactionAction transactionAction, TransactionType transactionType);
    Long countByAccountNoAndTransactionAction(Long accountNo, TransactionAction transactionAction);
    Long countByAccountNoAndTransactionType(Long accountNo, TransactionType transactionType);
    Long countByAccountNo(Long accountNo);
    Optional<Transactions> findFirstByAccountNoOrderByTransactionDateDesc(Long accountNo);
}
