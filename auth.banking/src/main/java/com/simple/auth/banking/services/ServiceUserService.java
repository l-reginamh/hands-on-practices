package com.simple.auth.banking.services;

import com.simple.auth.banking.model.dto.ServiceUserDto;
import com.simple.auth.banking.model.entity.ServiceUser;
import com.simple.auth.banking.model.request.LoginRequest;
import com.simple.auth.banking.model.request.ServiceUserRequest;

public interface ServiceUserService {
    ServiceUser getServiceUser(LoginRequest loginRequest);
    ServiceUser createServiceUser(ServiceUserRequest serviceUserRequest);
    ServiceUser updateServiceUser(ServiceUserRequest serviceUserRequest);
    ServiceUser activateServiceUser(ServiceUserRequest serviceUserRequest);
    ServiceUser deactivateServiceUser(ServiceUserRequest serviceUserRequest);
    ServiceUserDto convertToDto(ServiceUser serviceUser);
    ServiceUser convertToEntity(ServiceUserDto serviceUserDto);
}
