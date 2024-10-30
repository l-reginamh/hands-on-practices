package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.repository.ServiceUserRepository;
import com.simple.auth.banking.utils.mappers.ServiceUserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceUserServiceImplTest {
    @InjectMocks
    private ServiceUserServiceImpl serviceUser;

    @Mock
    private ServiceUserRepository serviceUserRepository;

    @Mock
    private ServiceUserMapper serviceUserMapper;

    @Test
    void usernameAvailabilityCheck_resultTrue() {

    }

    @Test
    void usernameAvailabilityCheck_resultFalse() {

    }

    @Test
    void customerAvailabilityCheck_resultTrue() {

    }

    @Test
    void customerAvailabilityCheck_resultFalse() {

    }

    @Test
    void getServiceUser_resultDataNotFoundException() {

    }

    @Test
    void getServiceUser_resultSuccess() {

    }

    @Test
    void createServiceUser_resultAlreadyExistsException_customerExist() {

    }

    @Test
    void createServiceUser_resultAlreadyExistsException_usernameTaken() {

    }

    @Test
    void createServiceUser_resultSuccess() {

    }

    @Test
    void updateServiceUser_resultDataNotFoundException() {

    }

    @Test
    void updateServiceUser_resultSuccess() {

    }

    @Test
    void activateServiceUser_resultDataNotFoundException() {

    }

    @Test
    void activateServiceUser_resultSuccess() {

    }

    @Test
    void deactivateServiceUser_resultDataNotFoundException() {

    }

    @Test
    void deactivateServiceUser_resultSuccess() {

    }

    @Test
    void convertToDto() {

    }

    @Test
    void convertToEntity() {

    }
}
