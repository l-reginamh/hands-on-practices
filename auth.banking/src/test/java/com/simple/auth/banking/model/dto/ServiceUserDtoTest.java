package com.simple.auth.banking.model.dto;

import com.simple.auth.banking.constants.enums.UserStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ServiceUserDtoTest {
    @InjectMocks
    private ServiceUserDto serviceUserDto;

    @Test
    void serviceUserDtoTest() {
        serviceUserDto = new ServiceUserDto();
        serviceUserDto.setId(1L);
        serviceUserDto.setUsername("username");
        serviceUserDto.setCustomerId("910101101001");
        serviceUserDto.setUserStatus(UserStatus.ACTIVE);

        assertEquals(1L, serviceUserDto.getId());
        assertEquals("username", serviceUserDto.getUsername());
        assertEquals("910101101001", serviceUserDto.getCustomerId());
        assertEquals(UserStatus.ACTIVE, serviceUserDto.getUserStatus());
    }
}
