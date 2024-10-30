package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.dto.ContactDto;
import com.simple.auth.banking.model.entity.Contact;
import com.simple.auth.banking.model.request.ContactRequest;
import com.simple.auth.banking.repository.ContactRepository;
import com.simple.auth.banking.services.AccountService;
import com.simple.auth.banking.utils.mappers.ContactMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {
    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private ContactMapper contactMapper;

    @Test
    void getContactById_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> contactService.getContactById(100000001L));
        assertEquals(MessageConstants.CONTACT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getContactById_resultSuccess() {
        when(contactRepository.findById(anyLong())).thenReturn(java.util.Optional.of(createContact()));
        Contact result = contactService.getContactById(1L);
        assertEquals("0191234567", result.getMobile());
    }

    @Test
    void getContactByAccountNo_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> contactService.getContactByAccountNo(100000001L));
        assertEquals(MessageConstants.CONTACT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getContactByAccountNo_resultSuccess() {
        when(contactRepository.findByAccountNo(anyLong())).thenReturn(java.util.Optional.of(createContact()));
        Contact result = contactService.getContactByAccountNo(10000001L);
        assertEquals("0191234567", result.getMobile());
    }

    @Test
    void createContact_resultInvalidRequestException_invalidAccount() {
        when(accountService.customerAndAccountNoAvailabilityCheck(anyString(), anyLong())).thenReturn(false);
        Exception exception = assertThrows(InvalidRequestException.class, () -> contactService.createContact(createContactRequest()));
        assertEquals(MessageConstants.USER_NOT_AVAILABLE, exception.getMessage());
    }

    @Test
    void createContact_resultInvalidRequestException_invalidEmail() {
        when(accountService.customerAndAccountNoAvailabilityCheck(anyString(), anyLong())).thenReturn(true);
        ContactRequest contactRequest = createContactRequest();
        contactRequest.setEmail("testgmailcom");
        Exception exception = assertThrows(InvalidRequestException.class, () -> contactService.createContact(contactRequest));
        assertEquals(MessageConstants.CONTACT_INVALID_EMAIL, exception.getMessage());
    }

    @Test
    void createContact_resultInvalidRequestException_invalidMobile() {
        when(accountService.customerAndAccountNoAvailabilityCheck(anyString(), anyLong())).thenReturn(true);
        ContactRequest contactRequest = createContactRequest();
        contactRequest.setMobile("0192222");
        Exception exception = assertThrows(InvalidRequestException.class, () -> contactService.createContact(contactRequest));
        assertEquals(MessageConstants.CONTACT_INVALID_MOBILE, exception.getMessage());
    }

    @Test
    void createContact_resultSuccess() {
        when(contactRepository.save(any())).thenReturn(createContact());
        when(accountService.customerAndAccountNoAvailabilityCheck(anyString(), anyLong())).thenReturn(true);
        Contact contact = contactService.createContact(createContactRequest());
        assertEquals("0191234567", contact.getMobile());
    }

    @Test
    void updateContact_resultInvalidRequestException_invalidEmail() {
        ContactRequest contactRequest = createContactRequest();
        contactRequest.setEmail("testgmailcom");
        Exception exception = assertThrows(InvalidRequestException.class, () -> contactService.updateContact(1L, contactRequest));
        assertEquals(MessageConstants.CONTACT_INVALID_EMAIL, exception.getMessage());
    }

    @Test
    void updateContact_resultInvalidRequestException_invalidMobile() {
        ContactRequest contactRequest = createContactRequest();
        contactRequest.setMobile("0192222");
        Exception exception = assertThrows(InvalidRequestException.class, () -> contactService.updateContact(1L, contactRequest));
        assertEquals(MessageConstants.CONTACT_INVALID_MOBILE, exception.getMessage());
    }

    @Test
    void updateContact_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> contactService.updateContact(1L, createContactRequest()));
        assertEquals(MessageConstants.CONTACT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateContact_resultSuccess() {
        when(contactRepository.findById(anyLong())).thenReturn(java.util.Optional.of(createContact()));
        when(contactRepository.save(any())).thenReturn(createContact());
        Contact contact = contactService.updateContact(1L, createContactRequest());
        assertEquals("0191234567", contact.getMobile());
    }

    @Test
    void convertToDto() {
        when(contactMapper.convertToDto(any())).thenReturn(createContactDto());
        ContactDto result = contactService.convertToDto(createContact());
        assertNotNull(result);
    }

    @Test
    void convertToEntity() {
        when(contactMapper.convertToEntity(any())).thenReturn(createContact());
        Contact result = contactService.convertToEntity(createContactDto());
        assertNotNull(result);
    }

    private ContactRequest createContactRequest() {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setAccountNo(10000001L);
        contactRequest.setCustomerId("910101101001");
        contactRequest.setMobile("0191234567");
        contactRequest.setAddress("No 1, Jalan Indonesia");
        contactRequest.setEmail("testing@gmail.com");
        return contactRequest;
    }

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setAccountNo(10000001L);
        contact.setCustomerId("910101101001");
        contact.setMobile("0191234567");
        contact.setAddress("No 1, Jalan Malaysia");
        contact.setEmail("testing@gmail.com");
        return contact;
    }

    private ContactDto createContactDto() {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(1L);
        contactDto.setAccountNo(10000001L);
        contactDto.setCustomerId("910101101001");
        contactDto.setMobile("0191234567");
        contactDto.setAddress("No 1, Jalan Malaysia");
        contactDto.setEmail("testing@gmail.com");
        return contactDto;
    }
}
