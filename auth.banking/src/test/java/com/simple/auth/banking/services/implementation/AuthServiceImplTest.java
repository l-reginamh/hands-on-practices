package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.repository.ServiceUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private ServiceUserRepository serviceUserRepository;

    @Test
    void loadUserByUsername_resultNull() {
        
    }

    @Test
    void loadUserByUsername_resultNotNull() {
        
    }

    @Test
    void createJwtToken() {
        
    }
}
