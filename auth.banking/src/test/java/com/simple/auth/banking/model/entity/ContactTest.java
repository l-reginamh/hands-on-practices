package com.simple.auth.banking.model.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ContactTest {
    @InjectMocks
    private Contact contact;

    @Test
    void contactTest() {
        contact = new Contact();
        contact.setId(1L);
        contact.setAccountNo(10000001L);
        contact.setCustomerId("910101101001");
        contact.setMobile("0191234567");
        contact.setAddress("No 1, Jalan Malaysia");
        contact.setEmail("testing@gmail.com");
        contact.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
        contact.setModifiedDate(new Date(Calendar.getInstance().getTimeInMillis()));

        assertEquals(1L, contact.getId());
        assertEquals(10000001L, contact.getAccountNo());
        assertEquals("910101101001", contact.getCustomerId());
        assertEquals("0191234567", contact.getMobile());
        assertEquals("No 1, Jalan Malaysia", contact.getAddress());
        assertEquals("testing@gmail.com", contact.getEmail());
        assertNotNull(contact.getCreatedDate());
        assertNotNull(contact.getModifiedDate());
    }
}
