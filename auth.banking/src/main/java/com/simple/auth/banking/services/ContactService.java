package com.simple.auth.banking.services;

import com.simple.auth.banking.model.dto.ContactDto;
import com.simple.auth.banking.model.entity.Contact;
import com.simple.auth.banking.model.request.ContactRequest;

public interface ContactService {
    Contact getContactById(Long id);
    Contact getContactByAccountNo(Long accountNo);
    Contact createContact(ContactRequest contactRequest);
    Contact updateContact(Long id, ContactRequest contactRequest);
    ContactDto convertToDto(Contact contact);
    Contact convertToEntity(ContactDto contactDto);
}
