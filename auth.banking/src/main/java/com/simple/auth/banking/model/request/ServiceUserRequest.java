package com.simple.auth.banking.model.request;

import lombok.Data;

@Data
public class ServiceUserRequest {
    private String username;
    private String password;
    private String customerId;
}
