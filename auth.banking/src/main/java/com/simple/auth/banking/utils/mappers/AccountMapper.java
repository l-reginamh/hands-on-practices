package com.simple.auth.banking.utils.mappers;

import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.dto.ServiceUserDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.entity.ServiceUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto convertToDto(Account account);
    Account convertToEntity(AccountDto accountDto);
}
