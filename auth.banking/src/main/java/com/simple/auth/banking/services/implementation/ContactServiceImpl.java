package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.dto.ContactDto;
import com.simple.auth.banking.model.entity.Contact;
import com.simple.auth.banking.model.request.ContactRequest;
import com.simple.auth.banking.repository.ContactRepository;
import com.simple.auth.banking.services.AccountService;
import com.simple.auth.banking.services.ContactService;
import com.simple.auth.banking.utils.ContactValidationUtils;
import com.simple.auth.banking.utils.InputValidationUtils;
import com.simple.auth.banking.utils.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final AccountService accountService;
    private final ContactMapper contactMapper;

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new DataNotFoundException(MessageConstants.CONTACT_NOT_FOUND));
    }

    @Override
    public Contact getContactByAccountNo(Long accountNo) {
        return contactRepository.findByAccountNo(accountNo).orElseThrow(() -> new DataNotFoundException(MessageConstants.CONTACT_NOT_FOUND));
    }

    @Override
    public Contact createContact(ContactRequest contactRequest) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        if (!accountService.customerAndAccountNoAvailabilityCheck(contactRequest.getCustomerId(), contactRequest.getAccountNo())) {
            throw new InvalidRequestException("Invalid customer or account number, please check again or contact customer service.");
        }

        if (!ContactValidationUtils.emailCheck(contactRequest.getEmail())) {
            throw new InvalidRequestException("Invalid email.");
        }

        if (!ContactValidationUtils.mobileCheck(contactRequest.getMobile())) {
            throw new InvalidRequestException("Invalid mobile.");
        }

        Contact contact = Contact.builder()
                .accountNo(contactRequest.getAccountNo())
                .customerId(contactRequest.getCustomerId())
                .address(contactRequest.getAddress())
                .email(contactRequest.getEmail())
                .mobile(contactRequest.getMobile())
                .createdDate(currentDate)
                .modifiedDate(currentDate)
                .build();

        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Long id, ContactRequest contactRequest) {
        if (!ContactValidationUtils.emailCheck(contactRequest.getEmail())) {
            throw new InvalidRequestException("Invalid email.");
        }

        if (!ContactValidationUtils.mobileCheck(contactRequest.getMobile())) {
            throw new InvalidRequestException("Invalid mobile.");
        }
        return contactRepository.findById(id)
                .map(existingContact -> updateContactDetails(existingContact, contactRequest))
                .map(contactRepository::save)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.CONTACT_NOT_FOUND));
    }

    private Contact updateContactDetails(Contact contact, ContactRequest contactRequest) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        contact.setAccountNo(InputValidationUtils.inputValidation(contact.getAccountNo(), contactRequest.getAccountNo()));
        contact.setCustomerId(InputValidationUtils.inputValidation(contact.getCustomerId(), contactRequest.getCustomerId()));
        contact.setMobile(InputValidationUtils.inputValidation(contact.getMobile(), contactRequest.getMobile()));
        contact.setAddress(InputValidationUtils.inputValidation(contact.getAddress(), contactRequest.getAddress()));
        contact.setEmail(InputValidationUtils.inputValidation(contact.getEmail(), contactRequest.getEmail()));
        contact.setModifiedDate(currentDate);
        return contact;
    }

    @Override
    public ContactDto convertToDto(Contact contact) {
        return contactMapper.convertToDto(contact);
    }

    @Override
    public Contact convertToEntity(ContactDto contactDto) {
        return contactMapper.convertToEntity(contactDto);
    }
}
