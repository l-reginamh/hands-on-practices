package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.enums.UserRole;
import com.simple.auth.banking.constants.enums.UserStatus;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.model.dto.ServiceUserDto;
import com.simple.auth.banking.model.entity.ServiceUser;
import com.simple.auth.banking.model.request.ServiceUserRequest;
import com.simple.auth.banking.repository.ServiceUserRepository;
import com.simple.auth.banking.utils.mappers.ServiceUserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceUserServiceImplTest {
    @InjectMocks
    private ServiceUserServiceImpl serviceUserService;

    @Mock
    private ServiceUserRepository serviceUserRepository;

    @Mock
    private ServiceUserMapper serviceUserMapper;

    @Test
    void usernameAvailabilityCheck_resultTrue() {
        when(serviceUserRepository.existsByUsername(anyString())).thenReturn(true);
        assertTrue(serviceUserService.usernameAvailabilityCheck("test"));
    }

    @Test
    void usernameAvailabilityCheck_resultFalse() {
        when(serviceUserRepository.existsByUsername(anyString())).thenReturn(false);
        assertFalse(serviceUserService.usernameAvailabilityCheck("test"));
    }

    @Test
    void customerAvailabilityCheck_resultTrue() {
        when(serviceUserRepository.existsByCustomerId(anyString())).thenReturn(true);
        assertTrue(serviceUserService.customerAvailabilityCheck("test"));
    }

    @Test
    void customerAvailabilityCheck_resultFalse() {
        when(serviceUserRepository.existsByCustomerId(anyString())).thenReturn(false);
        assertFalse(serviceUserService.customerAvailabilityCheck("test"));
    }

    @Test
    void getServiceUser_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> serviceUserService.getServiceUser("test"));
        assertEquals(MessageConstants.USER_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getServiceUser_resultSuccess() {
        when(serviceUserRepository.findByUsername(anyString())).thenReturn(Optional.of(createServiceUser()));
        assertEquals("username", serviceUserService.getServiceUser("test").getUsername());
    }

    @Test
    void createServiceUser_resultAlreadyExistsException_customerExist() {
        when(serviceUserRepository.existsByCustomerId(anyString())).thenReturn(true);
        Exception exception = assertThrows(AlreadyExistsException.class, () -> serviceUserService.createServiceUser(createServiceUserRequest()));
        assertEquals(MessageConstants.USER_ALREADY_EXIST, exception.getMessage());
    }

    @Test
    void createServiceUser_resultAlreadyExistsException_usernameTaken() {
        when(serviceUserRepository.existsByUsername(anyString())).thenReturn(true);
        Exception exception = assertThrows(AlreadyExistsException.class, () -> serviceUserService.createServiceUser(createServiceUserRequest()));
        assertEquals(MessageConstants.USER_USERNAME_TAKEN, exception.getMessage());
    }

    @Test
    void createServiceUser_resultSuccess() {
        when(serviceUserRepository.existsByCustomerId(anyString())).thenReturn(false);
        when(serviceUserRepository.existsByUsername(anyString())).thenReturn(false);
        when(serviceUserRepository.save(any())).thenReturn(createServiceUser());
        assertEquals("username", serviceUserService.createServiceUser(createServiceUserRequest()).getUsername());
    }

    @Test
    void updateServiceUser_resultDataNotFoundException() {
        Exception exception = assertThrows(DataNotFoundException.class, () -> serviceUserService.updateServiceUser(1L, createServiceUserRequest()));
        assertEquals(MessageConstants.USER_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateServiceUser_resultSuccess() {
        when(serviceUserRepository.findById(anyLong())).thenReturn(Optional.of(createServiceUser()));
        when(serviceUserRepository.save(any())).thenReturn(createServiceUser());
        assertEquals("username", serviceUserService.updateServiceUser(1L, createServiceUserRequest()).getUsername());
    }

    @Test
    void activateServiceUser_resultDataNotFoundException() {
        when(serviceUserRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(DataNotFoundException.class, () -> serviceUserService.activateServiceUser(1L));
        assertEquals(MessageConstants.USER_NOT_FOUND, exception.getMessage());
    }

    @Test
    void activateServiceUser_resultSuccess() {
        ServiceUser serviceUser = createServiceUser();
        serviceUser.setUserStatus(UserStatus.DEACTIVATED);
        when(serviceUserRepository.findById(anyLong())).thenReturn(Optional.of(serviceUser));
        when(serviceUserRepository.save(any())).thenReturn(createServiceUser());
        ServiceUser result = serviceUserService.activateServiceUser(1L);
        assertEquals("username", result.getUsername());
    }

    @Test
    void deactivateServiceUser_resultDataNotFoundException() {
        when(serviceUserRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(DataNotFoundException.class, () -> serviceUserService.deactivateServiceUser(1L));
        assertEquals(MessageConstants.USER_NOT_FOUND, exception.getMessage());
    }

    @Test
    void deactivateServiceUser_resultSuccess() {
        ServiceUser serviceUser = createServiceUser();
        serviceUser.setUserStatus(UserStatus.ACTIVE);
        when(serviceUserRepository.findById(anyLong())).thenReturn(Optional.of(serviceUser));
        when(serviceUserRepository.save(any())).thenReturn(createServiceUser());
        assertEquals("username", serviceUserService.deactivateServiceUser(1L).getUsername());
    }

    @Test
    void convertToDto() {
        when(serviceUserMapper.convertToDto(any())).thenReturn(createServiceUserDto());
        ServiceUserDto result = serviceUserService.convertToDto(createServiceUser());
        assertNotNull(result);
    }

    @Test
    void convertToEntity() {
        when(serviceUserMapper.convertToEntity(any())).thenReturn(createServiceUser());
        ServiceUser result = serviceUserService.convertToEntity(createServiceUserDto());
        assertNotNull(result);
    }

    private ServiceUserDto createServiceUserDto() {
        ServiceUserDto serviceUserDto = new ServiceUserDto();
        serviceUserDto.setUsername("username");
        serviceUserDto.setCustomerId("910101101001");
        serviceUserDto.setUserStatus(UserStatus.ACTIVE);
        return serviceUserDto;
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

    private ServiceUserRequest createServiceUserRequest() {
        ServiceUserRequest serviceUserRequest = new ServiceUserRequest();
        serviceUserRequest.setUsername("username");
        serviceUserRequest.setPassword("password");
        serviceUserRequest.setCustomerId("910101101001");
        serviceUserRequest.setRole(UserRole.PERSONAL);
        return serviceUserRequest;
    }
}
