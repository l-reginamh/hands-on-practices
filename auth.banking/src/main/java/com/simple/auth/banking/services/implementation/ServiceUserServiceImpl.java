package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.enums.UserStatus;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.model.dto.ServiceUserDto;
import com.simple.auth.banking.model.entity.ServiceUser;
import com.simple.auth.banking.model.request.LoginRequest;
import com.simple.auth.banking.model.request.ServiceUserRequest;
import com.simple.auth.banking.repository.ServiceUserRepository;
import com.simple.auth.banking.services.ServiceUserService;
import com.simple.auth.banking.utils.mappers.ServiceUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@RequiredArgsConstructor
@Service
public class ServiceUserServiceImpl implements ServiceUserService {
    private final ServiceUserMapper serviceUserMapper;
    private final ServiceUserRepository serviceUserRepository;

    private boolean usernameEligibilityCheck(ServiceUserRequest serviceUserRequest) {
        return serviceUserRepository.existsByUsername(serviceUserRequest.getUsername());
    }

    private boolean customerAvailabilityCheck(ServiceUserRequest serviceUserRequest) {
        return serviceUserRepository.existsByCustomerId(serviceUserRequest.getCustomerId());
    }

    @Override
    public ServiceUser getServiceUser(LoginRequest loginRequest) {
        return serviceUserRepository.findByUsername(loginRequest.getUsername());
    }

    @Override
    public ServiceUser createServiceUser(ServiceUserRequest serviceUserRequest) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

        if (customerAvailabilityCheck(serviceUserRequest)) {
            throw new AlreadyExistsException("Customer exist, please proceed to login.");
        }

        if (usernameEligibilityCheck(serviceUserRequest)) {
            throw new AlreadyExistsException("Username taken.");
        }

        ServiceUser serviceUserApplication = new ServiceUser();
        serviceUserApplication.setUsername(serviceUserRequest.getUsername());
        serviceUserApplication.setPassword(bCryptEncoder.encode(serviceUserRequest.getPassword()));
        serviceUserApplication.setRole("CUSTOMER");
        serviceUserApplication.setCustomerId(serviceUserRequest.getCustomerId());
        serviceUserApplication.setUserStatus(UserStatus.ACTIVE);
        serviceUserApplication.setCreatedDate(currentDate);
        serviceUserApplication.setUpdatedDate(currentDate);

        return serviceUserRepository.save(serviceUserApplication);
    }

    @Override
    public ServiceUser updateServiceUser(ServiceUserRequest serviceUserRequest) {
        return null;
    }

    @Override
    public ServiceUser activateServiceUser(ServiceUserRequest serviceUserRequest) {
        return null;
    }

    @Override
    public ServiceUser deactivateServiceUser(ServiceUserRequest serviceUserRequest) {
        return null;
    }

    @Override
    public ServiceUserDto convertToDto(ServiceUser serviceUser) {
        return serviceUserMapper.convertToDto(serviceUser);
    }

    @Override
    public ServiceUser convertToEntity(ServiceUserDto serviceUserDto) {
        return serviceUserMapper.convertToEntity(serviceUserDto);
    }
}
