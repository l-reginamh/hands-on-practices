package com.simple.auth.banking.services;

import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.request.AccountRequest;

import java.util.List;

public interface AccountService {
    boolean customerAndAccountNoAvailabilityCheck(String customerId, Long accountNo);
    boolean existsAccountByAccountNo(Long accountNo);
    Account getAccountById(Long id);
    Account getAccountByAccountNo(Long accountNo);
    List<Account> getAccountsByCustomerId(String customerId);
    Account createAccount(AccountRequest accountRequest);
    Account updateAccount(Long id, AccountRequest accountRequest);
    Account activateAccount(Long id, AccountRequest accountRequest);
    Account deactivateAccount(Long id, AccountRequest accountRequest);
    AccountDto convertToDto(Account account);
    Account convertToEntity(AccountDto accountDto);
}
