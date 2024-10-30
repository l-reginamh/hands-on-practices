package com.simple.auth.banking.controller;

import com.simple.auth.banking.services.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {
    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactService contactService;

    @Test
    void getContact_resultDataFoundException() {
        
    }

    @Test
    void getContact_resultException() {
        
    }

    @Test
    void getContact_resultSuccess() {
        
    }
  
    @Test
    void createContact_resultAlreadyExistsException() {
        
    }

    @Test
    void createContact_resultException() {
        
    }

    @Test
    void createContact_resultSuccess() {
        
    }

    @Test
    void updateContact_resultDataNotFoundException() {
        
    }

    @Test
    void updateContact_resultException() {
        
    }

    @Test
    void updateContact_resultSuccess() {
        
    }
}
