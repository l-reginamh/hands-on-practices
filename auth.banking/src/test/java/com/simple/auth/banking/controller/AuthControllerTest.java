package com.simple.auth.banking.controller;

import com.simple.auth.banking.services.AuthService;
import com.simple.auth.banking.services.ServiceUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Mock
    private ServiceUserService serviceUserService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void register_resultValidationFailed() {
        
    }

    @Test
    void register_resultAlreadyExistsException() {
        
    }

    @Test
    void register_resultException() {
        
    }

    @Test
    void register_resultSuccess() {
        
    }

    @Test
    void login_resultValidationFailed() {
        
    }

    @Test
    void login_resultDataNotFoundException() {
        
    }

    @Test
    void login_resultException() {
        
    }

    @Test
    void login_resultSuccess() {
        
    }

    @Test
    void profile_resultSuccess() {
        
    }
}
