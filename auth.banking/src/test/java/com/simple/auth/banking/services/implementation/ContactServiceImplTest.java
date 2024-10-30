package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.repository.ContactRepository;
import com.simple.auth.banking.services.AccountService;
import com.simple.auth.banking.utils.mappers.ContactMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    }

    @Test
    void getContactById_resultSuccess() {

    }

    @Test
    void getContactByAccountNo_resultDataNotFoundException() {

    }

    @Test
    void getContactByAccountNo_resultSuccess() {

    }

    @Test
    void createContact_resultInvalidRequestException_invalidAccount() {

    }

    @Test
    void createContact_resultInvalidRequestException_invalidEmail() {

    }

    @Test
    void createContact_resultInvalidRequestException_invalidMobile() {

    }

    @Test
    void createContact_resultSuccess() {

    }

    @Test
    void updateContact_resultInvalidRequestException_invalidEmail() {

    }

    @Test
    void updateContact_resultInvalidRequestException_invalidMobile() {

    }

    @Test
    void updateContact_resultDataNotFoundException() {

    }

    @Test
    void updateContact_resultSuccess() {

    }

    @Test
    void convertToDto() {

    }

    @Test
    void convertToEntity() {

    }
}
