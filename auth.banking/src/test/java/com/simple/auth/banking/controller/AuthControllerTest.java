package com.simple.auth.banking.controller;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @InjectMocks
    private AuthController authController

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
