package com.simple.auth.banking.model.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ContactDtoTest {
    @InjectMocks
    private ContactDto contactDto;

    @Test
    void contactDtoTest() {
        contactDto = new ContactDto();
        contactDto.setId(1L);
        contactDto.setAccountNo(10000001L);
        contactDto.setCustomerId("910101101001");
        contactDto.setMobile("0191234567");
        contactDto.setAddress("No 1, Jalan Malaysia");
        contactDto.setEmail("testing@gmail.com");

        assertEquals(1L, contactDto.getId());
        assertEquals(10000001L, contactDto.getAccountNo());
        assertEquals("910101101001", contactDto.getCustomerId());
        assertEquals("0191234567", contactDto.getMobile());
        assertEquals("No 1, Jalan Malaysia", contactDto.getAddress());
        assertEquals("testing@gmail.com", contactDto.getEmail());
    }
}
