package com.simple.auth.banking.services.implementation;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.enums.UserStatus;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.dto.ServiceUserDto;
import com.simple.auth.banking.model.entity.ServiceUser;
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
    public ServiceUser getServiceUser(String username) {
        return serviceUserRepository.findByUsername(username).orElseThrow(() -> new DataNotFoundException(MessageConstants.USER_NOT_FOUND));
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
    public ServiceUser updateServiceUser(Long serviceUserId, ServiceUserRequest serviceUserRequest) {
        return serviceUserRepository.findById(serviceUserId)
                .map(existingUser -> updateServiceUserDetails(existingUser, serviceUserRequest))
                .map(serviceUserRepository :: save)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.USER_NOT_FOUND));
    }

    private ServiceUser updateServiceUserDetails(ServiceUser serviceUser, ServiceUserRequest serviceUserRequest) {
        serviceUser.setPassword(serviceUserRequest.getPassword());
        serviceUser.setCustomerId(serviceUserRequest.getCustomerId());
        return serviceUser;
    }

    @Override
    public ServiceUser activateServiceUser(Long serviceUserId) {
        return serviceUserRepository.findById(serviceUserId)
                .map(existingUser -> updateServiceUserStatus(existingUser, UserStatus.ACTIVE))
                .map(serviceUserRepository :: save)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.USER_NOT_FOUND));
    }

    @Override
    public ServiceUser deactivateServiceUser(Long serviceUserId) {
        return serviceUserRepository.findById(serviceUserId)
                .map(existingUser -> updateServiceUserStatus(existingUser, UserStatus.DEACTIVATED))
                .map(serviceUserRepository :: save)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.USER_NOT_FOUND));
    }

    private ServiceUser updateServiceUserStatus(ServiceUser serviceUser, UserStatus newStatus) {
        if (serviceUser.getUserStatus().equals(UserStatus.CLOSED)) {
            throw new InvalidRequestException("User account has been closed, please contact customer service.");
        }
        if (serviceUser.getUserStatus().equals(newStatus)) {
            throw new InvalidRequestException("Invalid status update request.");
        }

        serviceUser.setUserStatus(newStatus);
        return serviceUser;
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
