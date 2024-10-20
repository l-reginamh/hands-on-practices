package com.simple.auth.banking.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
