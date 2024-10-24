package com.simple.auth.banking.services;

import com.simple.auth.banking.model.dto.ServiceUserDto;
import com.simple.auth.banking.model.entity.ServiceUser;
import com.simple.auth.banking.model.request.ServiceUserRequest;

public interface ServiceUserService {
    boolean usernameAvailabilityCheck(String username);
    boolean customerAvailabilityCheck(String customerId);
    ServiceUser getServiceUser(String username);
    ServiceUser createServiceUser(ServiceUserRequest serviceUserRequest);
    ServiceUser updateServiceUser(Long serviceUserId, ServiceUserRequest serviceUserRequest);
    ServiceUser activateServiceUser(Long serviceUserId);
    ServiceUser deactivateServiceUser(Long serviceUserId);
    ServiceUserDto convertToDto(ServiceUser serviceUser);
    ServiceUser convertToEntity(ServiceUserDto serviceUserDto);
}
