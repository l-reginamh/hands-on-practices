package com.simple.auth.banking.model.request;

import lombok.Data;

import java.sql.Date;

@Data
public class ContactRequest {
    private Long accountNo;

    private String customerId;

    private String mobile;

    private String address;

    private String email;
}
