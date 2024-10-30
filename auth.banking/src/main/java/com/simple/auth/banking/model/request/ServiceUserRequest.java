package com.simple.auth.banking.model.request;

import com.simple.auth.banking.constants.enums.UserRole;
import lombok.Data;

@Data
public class ServiceUserRequest {
    private String username;
    private String password;
    private String customerId;
    private UserRole role;
}
