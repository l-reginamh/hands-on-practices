package com.simple.auth.banking.services.implementation;

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