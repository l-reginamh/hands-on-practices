package com.simple.auth.banking.services/implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.enums.AccountType;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.request.AccountRequest;
import com.simple.auth.banking.repository.AccountRepository;
import com.simple.auth.banking.services.implementation.AccountServiceImpl;
import com.simple.auth.banking.services.implementation.ServiceUserServiceImpl;
import com.simple.auth.banking.utils.mappers.AccountMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ServiceUserServiceImpl serviceUserService;

    @Mock
    private AccountMapper accountMapper;

    @Test
    void customerAndAccountNoAvailabilityCheck_resultTrue() {
        when(accountRepository.existsByAccountNoAndCustomerId(anyLong(), anyString())).thenReturn(true);
        boolean result = accountService.customerAndAccountNoAvailabilityCheck("990101101001", 100000001L);
        assertTrue(result);
    }
    @Test
    void customerAndAccountNoAvailabilityCheck_resultFalse() {
        when(accountRepository.existsByAccountNoAndCustomerId(anyLong(), anyString())).thenReturn(false);
        boolean result = accountService.customerAndAccountNoAvailabilityCheck("990101101001", 100000001L);
        assertFalse(result);
    }

    @Test
    void existsAccountByAccountNo_resultTrue() {
        when(accountRepository.existsByAccountNo(anyLong())).thenReturn(true);
        boolean result = accountService.existsAccountByAccountNo(100000001L);
        assertTrue(result);
    }

    @Test
    void existsAccountByAccountNo_resultFalse() {
        when(accountRepository.existsByAccountNo(anyLong())).thenReturn(false);
        boolean result = accountService.existsAccountByAccountNo(100000001L);
        assertFalse(result);
    }

    @Test
    void customerAndAccountTypeCheck_resultTrue() {
        when(accountRepository.existsByCustomerIdAndAccountType(anyString(), any())).thenReturn(true);
        boolean result = accountService.customerAndAccountTypeCheck("990101101001", AccountType.SAVINGS);
        assertTrue(result);
    }

    @Test
    void customerAndAccountTypeCheck_resultFalse() {
        when(accountRepository.existsByCustomerIdAndAccountType(anyString(), any())).thenReturn(false);
        boolean result = accountService.customerAndAccountTypeCheck("990101101001", AccountType.SAVINGS);
        assertFalse(result);
    }

    @Test
    void getAccountById_resultAccountNotFound() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> accountService.getAccountById(100000001L));
        assertEquals(MessageConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getAccountById_resultAccountFound() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(mockAccount()));
        Account result = accountService.getAccountById(100000001L);
        assertNotNull(result);
    }

    @Test
    void getAccountByAccountNo_resultAccountNotFound() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> accountService.getAccountByAccountNo(100000001L));
        assertEquals(MessageConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getAccountByAccountNo_resultAccountFound() {
        when(accountRepository.findByAccountNo(anyLong())).thenReturn(Optional.ofNullable(mockAccount()));
        Account result = accountService.getAccountByAccountNo(100000001L);
        assertNotNull(result);
    }

    @Test
    void getAccountsByCustomerId_resultAccountFound() {
        when(accountRepository.findByCustomerId(anyString())).thenReturn(mockAccountList());
        List<Account> result = accountService.getAccountsByCustomerId("990101101001");
        assertEquals(1, result.size());
    }

    @Test
    void createAccount_resultThrowInvalidRequestException() {
        AccountRequest accountRequest = mockAccountRequest();
        Exception exception = assertThrows(InvalidRequestException.class, () -> accountService.createAccount(accountRequest));
        assertEquals(MessageConstants.USER_NOT_REGISTERED, exception.getMessage());
    }

    @Test
    void createAccount_resultThrowAlreadyExistsException() {
        when(accountService.customerAndAccountTypeCheck(anyString(), any())).thenReturn(true);

        when(serviceUserService.customerAvailabilityCheck(anyString())).thenReturn(true);

        AccountRequest accountRequest = mockAccountRequest();
        Exception exception = assertThrows(AlreadyExistsException.class, () -> accountService.createAccount(accountRequest));
        assertEquals(MessageConstants.USER_ACCOUNT_APPLIED, exception.getMessage());
    }

    @Test
    void createAccount_resultSuccess() {
        when(serviceUserService.customerAvailabilityCheck(anyString())).thenReturn(true);
        when(accountService.customerAndAccountTypeCheck(anyString(), any())).thenReturn(false);

        when(accountRepository.save(any(Account.class))).thenReturn(mockAccount());

        AccountRequest accountRequest = mockAccountRequest();
        Account result = accountService.createAccount(accountRequest);
        assertEquals(100000001L, result.getAccountNo());
    }

    @Test
    void updateAccount_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> updateAccountCall());
        assertEquals(MessageConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
    }

    private Account updateAccountCall() {
        return accountService.updateAccount(1L, mockAccountRequest());
    }

    @Test
    void updateAccount_resultSuccess() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(mockAccount()));
        when(accountRepository.save(any(Account.class))).thenReturn(mockAccount());
        Account result = accountService.updateAccount(1L, mockAccountRequest());
        assertEquals(100000001L, result.getAccountNo());
    }

    @Test
    void activateAccount_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> activateAccountCall());
        assertEquals(MessageConstants.USER_ACCOUNT_NOT_AVAILABLE, exception.getMessage());
    }

    private Account activateAccountCall() {
        return accountService.activateAccount(1L, mockAccountRequest());
    }

    @Test
    void activateAccount_resultSuccess() {
        when(accountRepository.existsByAccountNoAndCustomerId(anyLong(), anyString())).thenReturn(true);

        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(mockAccount()));
        when(accountRepository.save(any(Account.class))).thenReturn(mockAccount());

        Account account = accountService.activateAccount(1L, mockAccountRequest());
        assertEquals(100000001L, account.getAccountNo());
    }

    @Test
    void deactivateAccount_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> deactivateAccountCall());
        assertEquals(MessageConstants.USER_ACCOUNT_NOT_AVAILABLE, exception.getMessage());
    }

    private Account deactivateAccountCall() {
        return accountService.deactivateAccount(1L, mockAccountRequest());
    }

    @Test
    void deactivateAccount_resultSuccess() {
        when(accountRepository.existsByAccountNoAndCustomerId(anyLong(), anyString())).thenReturn(true);

        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(mockAccount()));
        when(accountRepository.save(any(Account.class))).thenReturn(mockAccount());

        Account account = accountService.deactivateAccount(1L, mockAccountRequest());
        assertEquals(100000001L, account.getAccountNo());
    }

    @Test
    void convertToDto() {
        when(accountMapper.convertToDto(any())).thenReturn(mockAccountDto());
        AccountDto result = accountService.convertToDto(mockAccount());
        assertNotNull(result);
    }

    @Test
    void convertToEntity() {
        when(accountMapper.convertToEntity(any())).thenReturn(mockAccount());
        Account result = accountService.convertToEntity(mockAccountDto());
        assertNotNull(result);
    }

    private AccountRequest mockAccountRequest() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNo(100000001L);
        accountRequest.setCustomerId("990101101001");
        accountRequest.setAccountType(AccountType.SAVINGS);
        accountRequest.setName("John Doe");
        accountRequest.setCardNo("1234567890123456");
        accountRequest.setCardExpiry("01/22");
        accountRequest.setEncryptedCardNo("1234567890123456");
        return accountRequest;
    }

    private Account mockAccount() {
        Account account = new Account();
        account.setAccountNo(100000001L);
        account.setCustomerId("990101101001");
        account.setAccountType(AccountType.SAVINGS);
        account.setCardNo("1234567890123456");
        account.setCardExpiry("01/22");
        account.setEncryptedCardNo("1234567890123456");
        return account;
    }

    private AccountDto mockAccountDto() {
        AccountDto account = new AccountDto();
        account.setAccountNo(100000001L);
        account.setCustomerId("990101101001");
        account.setAccountType(AccountType.SAVINGS);
        account.setCardNo("1234567890123456");
        account.setCardExpiry("01/22");
        account.setEncryptedCardNo("1234567890123456");
        return account;
    }

    private List<Account> mockAccountList() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(mockAccount());
        return accountList;
    }
}
