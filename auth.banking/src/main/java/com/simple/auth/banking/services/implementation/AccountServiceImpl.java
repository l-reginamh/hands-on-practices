package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.request.AccountRequest;
import com.simple.auth.banking.repository.AccountRepository;
import com.simple.auth.banking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    @Override
    public Account getAccount(Long accountNo) {
        return accountRepository.findByAccountNo(accountNo).orElseThrow(() -> new DataNotFoundException(MessageConstants.USER_NOT_FOUND));
    }


    @Override
    public Account createAccount(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public Account updateAccount(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public AccountDto convertToDto(Account account) {
        return null;
    }

    @Override
    public Account convertToEntity(AccountDto accountDto) {
        return null;
    }
}
