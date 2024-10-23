package com.simple.auth.banking.utils.mappers;

import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.dto.TransactionsDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.entity.Transactions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionsMapper {
    TransactionsDto convertToDto(Transactions transactions);
    Transactions convertToEntity(TransactionsDto transactionsDto);
}
