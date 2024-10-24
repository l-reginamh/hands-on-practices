package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.enums.AccountStatus;
import com.simple.auth.banking.constants.enums.AccountType;
import com.simple.auth.banking.constants.enums.CardStatus;
import com.simple.auth.banking.constants.enums.TransactStatus;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.request.AccountRequest;
import com.simple.auth.banking.repository.AccountRepository;
import com.simple.auth.banking.services.AccountService;
import com.simple.auth.banking.services.ServiceUserService;
import com.simple.auth.banking.utils.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ServiceUserService serviceUserService;
    private final AccountMapper accountMapper;

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));
    }

    @Override
    public Account getAccount(Long accountNo) {
        return accountRepository.findByAccountNo(accountNo).orElseThrow(() -> new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));
    }

    @Override
    public Account createAccount(AccountRequest accountRequest) {
        Account lastAccount = accountRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));
        Account account = declareAccount(accountRequest, lastAccount);

        if (!serviceUserService.customerAvailabilityCheck(accountRequest.getCustomerId())) {
            throw new InvalidRequestException("Please register before applying for account.");
        }

        if (customerAndAccountCheck(account.getCustomerId(), account.getAccountType())) {
            throw new AlreadyExistsException("User has applied for this product.");
        }

        return accountRepository.save(account);
    }

    private static Account declareAccount(AccountRequest accountRequest, Account lastAccount) {
        Long newAccountId = lastAccount.getAccountNo() != null ? lastAccount.getAccountNo() + 1 : 100000001;
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        return Account.builder()
                .accountNo(newAccountId)
                .accountType(accountRequest.getAccountType())
                .name(accountRequest.getName())
                .customerId(accountRequest.getCustomerId())
                .accountStatus(AccountStatus.ACTIVE)
                .transactStatus(TransactStatus.ACTIVE)
                .transactionLimit(accountRequest.getTransactionLimit())
                .cardNo(accountRequest.getCardNo())
                .cardStatus(CardStatus.ACTIVE)
                .cardExpiry(accountRequest.getCardExpiry())
                .encryptedCardNo(accountRequest.getEncryptedCardNo())
                .createdDate(currentDate)
                .modifiedDate(currentDate)
                .build();
    }


    private boolean customerAndAccountCheck(String customerId, AccountType accountType) {
        return accountRepository.existsByCustomerIdAndAccountType(customerId, accountType);
    }

    @Override
    public Account updateAccount(Long id, AccountRequest accountRequest) {
        return accountRepository.findById(id)
                .map(existingAccount -> updateAccountDetails(existingAccount, accountRequest))
                .map(accountRepository::save)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));
    }

    private Account updateAccountDetails(Account account, AccountRequest accountRequest) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
        account.setName(accountRequest.getName());
        account.setCustomerId(accountRequest.getCustomerId());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setTransactStatus(TransactStatus.ACTIVE);
        account.setTransactionLimit(accountRequest.getTransactionLimit());
        account.setCardNo(accountRequest.getCardNo());
        account.setCardStatus(CardStatus.ACTIVE);
        account.setCardExpiry(accountRequest.getCardExpiry());
        account.setEncryptedCardNo(accountRequest.getEncryptedCardNo());
        account.setModifiedDate(currentDate);
        return account;
    }

    @Override
    public AccountDto convertToDto(Account account) {
        return accountMapper.convertToDto(account);
    }

    @Override
    public Account convertToEntity(AccountDto accountDto) {
        return accountMapper.convertToEntity(accountDto);
    }
}
