package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.DefaultValuesConstants;
import com.simple.auth.banking.constants.enums.AccountStatus;
import com.simple.auth.banking.constants.enums.AccountType;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.request.AccountRequest;
import com.simple.auth.banking.repository.AccountRepository;
import com.simple.auth.banking.services.AccountService;
import com.simple.auth.banking.services.ServiceUserService;
import com.simple.auth.banking.utils.InputValidationUtils;
import com.simple.auth.banking.utils.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ServiceUserService serviceUserService;
    private final AccountMapper accountMapper;

    @Override
    public boolean customerAndAccountNoAvailabilityCheck(String customerId, Long accountNo) {
        return accountRepository.existsByAccountNoAndCustomerId(accountNo, customerId);
    }

    @Override
    public boolean existsAccountByAccountNo(Long accountNo) {
        return accountRepository.existsByAccountNo(accountNo);
    }

    @Override
    public boolean customerAndAccountTypeCheck(String customerId, AccountType accountType) {
        return accountRepository.existsByCustomerIdAndAccountType(customerId, accountType);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));
    }

    @Override
    public Account getAccountByAccountNo(Long accountNo) {
        return accountRepository.findByAccountNo(accountNo).orElseThrow(() -> new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));
    }

    @Override
    public List<Account> getAccountsByCustomerId(String customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Override
    public Account createAccount(AccountRequest accountRequest) {
        Account lastAccount = accountRepository.findFirstByOrderByCreatedDateDesc().orElse(null);
        Account account = declareAccount(accountRequest, lastAccount);

        if (!serviceUserService.customerAvailabilityCheck(accountRequest.getCustomerId())) {
            throw new InvalidRequestException(MessageConstants.USER_NOT_REGISTERED);
        }

        if (customerAndAccountTypeCheck(account.getCustomerId(), account.getAccountType())) {
            throw new AlreadyExistsException(MessageConstants.USER_ACCOUNT_APPLIED);
        }

        return accountRepository.save(account);
    }

    private Account declareAccount(AccountRequest accountRequest, Account lastAccount) {
        Long newAccountId = lastAccount != null ? lastAccount.getAccountNo() + 1 : 100000001;
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        return Account.builder()
                .accountNo(newAccountId)
                .accountType(accountRequest.getAccountType())
                .name(accountRequest.getName())
                .customerId(accountRequest.getCustomerId())
                .accountStatus(DefaultValuesConstants.DEFAULT_ACCOUNT_STATUS)
                .transactStatus(DefaultValuesConstants.DEFAULT_TRANSACT_STATUS)
                .transactionLimit(DefaultValuesConstants.DEFAULT_TRANSACTION_LIMIT)
                .cardNo(accountRequest.getCardNo())
                .cardStatus(DefaultValuesConstants.DEFAULT_CARD_STATUS)
                .cardExpiry(accountRequest.getCardExpiry())
                .encryptedCardNo(accountRequest.getEncryptedCardNo())
                .createdDate(currentDate)
                .modifiedDate(currentDate)
                .build();
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

        account.setName(InputValidationUtils.inputValidation(account.getName(), accountRequest.getName()));
        account.setCustomerId(InputValidationUtils.inputValidation(account.getCustomerId(), accountRequest.getCustomerId()));
        account.setAccountStatus(InputValidationUtils.inputValidation(account.getAccountStatus(), accountRequest.getAccountStatus()));
        account.setTransactStatus(InputValidationUtils.inputValidation(account.getTransactStatus(), accountRequest.getTransactStatus()));
        account.setTransactionLimit(InputValidationUtils.inputValidation(account.getTransactionLimit(), accountRequest.getTransactionLimit()));
        account.setCardNo(InputValidationUtils.inputValidation(account.getCardNo(), accountRequest.getCardNo()));
        account.setCardStatus(InputValidationUtils.inputValidation(account.getCardStatus(), accountRequest.getCardStatus()));
        account.setCardExpiry(InputValidationUtils.inputValidation(account.getCardExpiry(), accountRequest.getCardExpiry()));
        account.setEncryptedCardNo(InputValidationUtils.inputValidation(account.getEncryptedCardNo(), accountRequest.getEncryptedCardNo()));
        account.setModifiedDate(currentDate);
        return account;
    }

    @Override
    public Account activateAccount(Long id, AccountRequest accountRequest) {
        if (!customerAndAccountNoAvailabilityCheck(accountRequest.getCustomerId(), accountRequest.getAccountNo())) {
            throw new DataNotFoundException("Account provided not available to proceed with this request");
        }
        return accountRepository.findById(id)
                .map(existingAccount -> updateAccountStatus(existingAccount, AccountStatus.ACTIVE))
                .map(accountRepository::save)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));
    }

    @Override
    public Account deactivateAccount(Long id, AccountRequest accountRequest) {
        if (!customerAndAccountNoAvailabilityCheck(accountRequest.getCustomerId(), accountRequest.getAccountNo())) {
            throw new DataNotFoundException(MessageConstants.USER_ACCOUNT_NOT_AVAILABLE);
        }
        return accountRepository.findById(id)
                .map(existingAccount -> updateAccountStatus(existingAccount, AccountStatus.INACTIVE))
                .map(accountRepository::save)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND));
    }

    private Account updateAccountStatus(Account account, AccountStatus accountStatus) {
        if (account.getAccountStatus() != accountStatus) {
            Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

            account.setAccountStatus(InputValidationUtils.inputValidation(account.getAccountStatus(), accountStatus));
            account.setModifiedDate(currentDate);
            return account;
        } else {
            throw new InvalidRequestException("Invalid request");
        }
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
