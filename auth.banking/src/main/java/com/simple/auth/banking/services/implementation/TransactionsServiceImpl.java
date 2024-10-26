package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.enums.TransactStatus;
import com.simple.auth.banking.constants.enums.TransactionAction;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.dto.TransactionsDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.entity.Transactions;
import com.simple.auth.banking.model.request.TransactionsRequest;
import com.simple.auth.banking.repository.TransactionsRepository;
import com.simple.auth.banking.services.AccountService;
import com.simple.auth.banking.services.TransactionsService;
import com.simple.auth.banking.utils.InputValidationUtils;
import com.simple.auth.banking.utils.mappers.TransactionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {
    private final TransactionsRepository transactionsRepository;
    private final AccountService accountService;
    private final TransactionsMapper transactionsMapper;

    @Override
    public boolean recipientAccountCheck(Long accountNo) {
        return accountService.existsAccountByAccountNo(accountNo);
    }

    @Override
    public boolean checkTransactStatus(TransactStatus transactStatus) {
        return transactStatus != TransactStatus.LOCKED;
    }

    @Override
    public boolean checkTransactionLimit(BigDecimal transactionLimit, BigDecimal debitValue) {
        return debitValue.compareTo(transactionLimit) <= 0;
    }

    @Override
    public boolean checkBalance(BigDecimal balance, BigDecimal debitValue) {
        return debitValue.compareTo(balance) <= 0;
    }

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
        if (!recipientAccountCheck(transactionsRequest.getCreditAccount())) {
            throw new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND);
        }
        Account account = accountService.getAccountByAccountNo(transactionsRequest.getAccountNo());

        if (!checkTransactStatus(account.getTransactStatus())) {
            throw new InvalidRequestException("Invalid status.");
        }

        if (!checkTransactionLimit(account.getTransactionLimit(), transactionsRequest.getDebitAmount())) {
            throw new InvalidRequestException("Exceed transaction limit.");
        }

        if (!checkBalance(transactionsRequest.getBalance(), transactionsRequest.getDebitAmount())) {
            throw new InvalidRequestException("Insufficient balance.");
        }

        Transactions senderTransaction = declareSenderTransaction(transactionsRequest);
        Transactions saveSenderTransaction = transactionsRepository.save(senderTransaction);

        Transactions recipientTransaction = declareRecipientTransaction(transactionsRequest);
        transactionsRepository.save(recipientTransaction);

        return saveSenderTransaction;
    }

    private Transactions declareSenderTransaction(TransactionsRequest transactionsRequest) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Transactions prevTransactions = transactionsRepository.findFirstByAccountNoOrderByTransactionDateDesc(transactionsRequest.getAccountNo()).orElse(null);
        if (prevTransactions == null || transactionsRequest.getTransactionAction() == TransactionAction.CREDIT) {
            throw new InvalidRequestException("Invalid transaction");
        }

        BigDecimal newBalance = prevTransactions == null ? prevTransactions.getBalance().subtract(transactionsRequest.getDebitAmount()) : transactionsRequest.getDebitAmount();

        return Transactions.builder()
                .accountNo(transactionsRequest.getAccountNo())
                .balance(newBalance)
                .transactionAction(transactionsRequest.getTransactionAction())
                .transactionType(transactionsRequest.getTransactionType())
                .transactionStatus(transactionsRequest.getTransactionStatus())
                .creditAccount(transactionsRequest.getCreditAccount())
                .debitAccount(transactionsRequest.getDebitAccount())
                .creditAmount(transactionsRequest.getCreditAmount())
                .debitAmount(transactionsRequest.getDebitAmount())
                .transactionDate(currentDate)
                .build();
    }

    private Transactions declareRecipientTransaction(TransactionsRequest transactionsRequest) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Transactions prevTransactions = transactionsRepository.findFirstByAccountNoOrderByTransactionDateDesc(transactionsRequest.getCreditAccount()).orElse(null);
        if (prevTransactions == null || transactionsRequest.getTransactionAction() == TransactionAction.DEBIT) {
            throw new InvalidRequestException("Invalid transaction");
        }

        BigDecimal newBalance = prevTransactions == null ? prevTransactions.getBalance().add(transactionsRequest.getDebitAmount()) : transactionsRequest.getDebitAmount();

        return Transactions.builder()
                .accountNo(transactionsRequest.getCreditAccount())
                .balance(newBalance)
                .transactionAction(TransactionAction.CREDIT)
                .transactionType(transactionsRequest.getTransactionType())
                .transactionStatus(transactionsRequest.getTransactionStatus())
                .creditAccount(transactionsRequest.getCreditAccount())
                .debitAccount(transactionsRequest.getDebitAccount())
                .creditAmount(transactionsRequest.getCreditAmount())
                .debitAmount(transactionsRequest.getDebitAmount())
                .transactionDate(currentDate)
                .build();
    }

    @Override
    public Transactions updateTransaction(Long id, TransactionsRequest transactionsRequest) {
        if (!recipientAccountCheck(transactionsRequest.getCreditAccount())) {
            throw new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND);
        }
        Account account = accountService.getAccountByAccountNo(transactionsRequest.getAccountNo());

        if (!checkTransactStatus(account.getTransactStatus())) {
            throw new InvalidRequestException("Invalid status.");
        }

        if (!checkTransactionLimit(account.getTransactionLimit(), transactionsRequest.getDebitAmount())) {
            throw new InvalidRequestException("Exceed transaction limit.");
        }

        if (!checkBalance(transactionsRequest.getBalance(), transactionsRequest.getDebitAmount())) {
            throw new InvalidRequestException("Insufficient balance.");
        }

        return transactionsRepository.findById(id)
                .map(existingTransaction -> updateTransactionsDetails(existingTransaction, transactionsRequest))
                .map(transactionsRepository::save)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.TRANSACTION_NOT_FOUND));
    }

    private Transactions updateTransactionsDetails(Transactions existingTransaction, TransactionsRequest transactionsRequest) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        existingTransaction.setAccountNo(InputValidationUtils.inputValidation(existingTransaction.getAccountNo(), transactionsRequest.getAccountNo()));
        existingTransaction.setBalance(InputValidationUtils.inputValidation(existingTransaction.getBalance(), transactionsRequest.getBalance()));
        existingTransaction.setTransactionAction(InputValidationUtils.inputValidation(existingTransaction.getTransactionAction(), transactionsRequest.getTransactionAction()));
        existingTransaction.setTransactionType(InputValidationUtils.inputValidation(existingTransaction.getTransactionType(), transactionsRequest.getTransactionType()));
        existingTransaction.setTransactionStatus(InputValidationUtils.inputValidation(existingTransaction.getTransactionStatus(), transactionsRequest.getTransactionStatus()));
        existingTransaction.setCreditAccount(InputValidationUtils.inputValidation(existingTransaction.getCreditAccount(), transactionsRequest.getCreditAccount()));
        existingTransaction.setDebitAccount(InputValidationUtils.inputValidation(existingTransaction.getDebitAccount(), transactionsRequest.getDebitAccount()));
        existingTransaction.setCreditAmount(InputValidationUtils.inputValidation(existingTransaction.getCreditAmount(), transactionsRequest.getCreditAmount()));
        existingTransaction.setDebitAmount(InputValidationUtils.inputValidation(existingTransaction.getDebitAmount(), transactionsRequest.getDebitAmount()));
        existingTransaction.setTransactionDate(currentDate);
        return existingTransaction;
    }

    @Override
    public TransactionsDto convertToDto(Transactions transactions) {
        return transactionsMapper.convertToDto(transactions);
    }

    @Override
    public Transactions convertToEntity(TransactionsDto transactionsDto) {
        return transactionsMapper.convertToEntity(transactionsDto);
    }
}
