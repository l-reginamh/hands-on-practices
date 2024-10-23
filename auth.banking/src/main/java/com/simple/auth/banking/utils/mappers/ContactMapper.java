package com.simple.auth.banking.utils.mappers;

import com.simple.auth.banking.model.dto.AccountDto;
import com.simple.auth.banking.model.dto.ContactDto;
import com.simple.auth.banking.model.entity.Account;
import com.simple.auth.banking.model.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDto convertToDto(Contact contact);
    Contact convertToEntity(ContactDto contactDto);
}
