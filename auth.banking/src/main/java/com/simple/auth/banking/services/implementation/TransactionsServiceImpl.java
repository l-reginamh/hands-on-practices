package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.model.entity.Transactions;
import com.simple.auth.banking.model.request.TransactionsRequest;
import com.simple.auth.banking.repository.TransactionsRepository;
import com.simple.auth.banking.services.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {
    private final TransactionsRepository transactionsRepository;
    @Override
    public List<Transactions> getTransactionsByAccountNo(Long accountNo) {
        return transactionsRepository.findAllByAccountNo(accountNo);
    }

    @Override
    public Transactions getTransactionById(Long id) {
        return transactionsRepository.findById(id).orElseThrow(() -> new DataNotFoundException(MessageConstants.TRANSACTION_NOT_FOUND));
    }

    @Override
    public Transactions createTransaction(TransactionsRequest transactionsRequest) {
        return null;
    }

    @Override
    public Transactions updateTransaction(Long id, TransactionsRequest transactionsRequest) {
        return null;
    }
}
