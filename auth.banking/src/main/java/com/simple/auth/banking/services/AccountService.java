package com.simple.auth.banking.services;

import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.request.AccountRequest;

public interface AccountService {
    Account getAccountById(Long id);
    
    Account getAccountByAccountNo(Long accountNo);

    List<Account> getAccountsByCustomerId(String customerId)

    Account createAccount(AccountRequest accountRequest);

    Account updateAccount(Long id, AccountRequest accountRequest);

    AccountDto convertToDto(Account account);

    Account convertToEntity(AccountDto accountDto);
}
