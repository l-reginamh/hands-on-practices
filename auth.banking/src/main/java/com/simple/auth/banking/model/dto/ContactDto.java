package com.simple.auth.banking.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ContactDto {
    private Long id;

    private Long accountNo;

    private String customerId;

    private String mobile;

    private String address;

    private String email;

    private Date createdDate;

    private Date modifiedDate;
}
