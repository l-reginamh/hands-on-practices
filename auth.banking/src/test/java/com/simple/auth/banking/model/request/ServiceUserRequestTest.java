package com.simple.auth.banking.model.request;

import com.simple.auth.banking.constants.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ServiceUserRequestTest {
    @InjectMocks
    private ServiceUserRequest serviceUserRequest;

    @Test
    void serviceUserRequestTest() {
        serviceUserRequest = new ServiceUserRequest();
        serviceUserRequest.setUsername("username");
        serviceUserRequest.setPassword("password");
        serviceUserRequest.setCustomerId("customerId");
        serviceUserRequest.setRole(UserRole.PERSONAL);

        assertEquals("username", serviceUserRequest.getUsername());
        assertEquals("password", serviceUserRequest.getPassword());
        assertEquals("customerId", serviceUserRequest.getCustomerId());
        assertEquals(UserRole.PERSONAL, serviceUserRequest.getRole());
    }
}
