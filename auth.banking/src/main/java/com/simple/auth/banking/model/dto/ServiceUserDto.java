package com.simple.auth.banking.model.dto;

import com.simple.auth.banking.constants.enums.UserStatus;
import lombok.Data;

@Data
public class ServiceUserDto {
    private Long id;

    private String username;

    private String customerId;

    private UserStatus userStatus;
}
