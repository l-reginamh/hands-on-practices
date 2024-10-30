package com.simple.auth.banking.model.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LoginRequestTest {
    @InjectMocks
    private LoginRequest loginRequest;

    @Test
    void loginRequestTest() {
        loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        assertEquals("username", loginRequest.getUsername());
        assertEquals("password", loginRequest.getPassword());
    }
}
