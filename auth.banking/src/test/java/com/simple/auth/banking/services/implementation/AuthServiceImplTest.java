package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.enums.UserRole;
import com.simple.auth.banking.constants.enums.UserStatus;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.model.entity.ServiceUser;
import com.simple.auth.banking.repository.ServiceUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@TestPropertySource(properties = """
    security.jwt.secret-key=key
    security.jwt.issuer=issuer
    security.jwt.expiration-time=3600000
    security.jwt.refresh-expiration-time=3600000
    """)
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private ServiceUserRepository serviceUserRepository;

    @Test
    void loadUserByUsername_resultNull() {
        when(serviceUserRepository.findByUsername(anyString())).thenThrow(new DataNotFoundException(""));
        Exception exception = assertThrows(DataNotFoundException.class, () -> authService.loadUserByUsername("username"));
        assertNotNull(exception);
    }

    @Test
    void loadUserByUsername_resultNotNull() {
        ServiceUser serviceUser = createServiceUser();
        when(serviceUserRepository.findByUsername(anyString())).thenReturn(Optional.of(serviceUser));
        assertNotNull(authService.loadUserByUsername("username"));
    }

    @Test
    void createJwtToken() {
        ReflectionTestUtils.setField(authService, "jwtSecretKey", "ke23456789765463554657684635456787654465789y");
        ReflectionTestUtils.setField(authService, "jwtIssuer", "issuer");
        ReflectionTestUtils.setField(authService, "jwtExpirationTime", "3600000");
        ReflectionTestUtils.setField(authService, "jwtRefreshExpirationTime", "3600000");
        assertNotNull(authService.createJwtToken(createServiceUser()));
    }

    private ServiceUser createServiceUser() {
        ServiceUser serviceUser = new ServiceUser();
        serviceUser.setUsername("username");
        serviceUser.setPassword("password");
        serviceUser.setCustomerId("910101101001");
        serviceUser.setUserStatus(UserStatus.ACTIVE);
        serviceUser.setRole(UserRole.PERSONAL);
        serviceUser.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
        serviceUser.setModifiedDate(new Date(Calendar.getInstance().getTimeInMillis()));
        return serviceUser;
    }
}
