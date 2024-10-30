package com.simple.auth.banking.model.entity;

import com.simple.auth.banking.constants.enums.UserRole;
import com.simple.auth.banking.constants.enums.UserStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ServiceUserTest {
    @InjectMocks
    private ServiceUser serviceUser;

    @Test
    void serviceUserTest() {
        serviceUser.setId(1L);
        serviceUser.setUsername("username");
        serviceUser.setPassword("password");
        serviceUser.setCustomerId("910101101001");
        serviceUser.setUserStatus(UserStatus.ACTIVE);
        serviceUser.setRole(UserRole.PERSONAL);
        serviceUser.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
        serviceUser.setModifiedDate(new Date(Calendar.getInstance().getTimeInMillis()));

        assertEquals(1L, serviceUser.getId());
        assertEquals("username", serviceUser.getUsername());
        assertEquals("password", serviceUser.getPassword());
        assertEquals("910101101001", serviceUser.getCustomerId());
        assertEquals(UserStatus.ACTIVE, serviceUser.getUserStatus());
        assertEquals(UserRole.PERSONAL, serviceUser.getRole());
        assertNotNull(serviceUser.getCreatedDate());
        assertNotNull(serviceUser.getModifiedDate());
    }
}
