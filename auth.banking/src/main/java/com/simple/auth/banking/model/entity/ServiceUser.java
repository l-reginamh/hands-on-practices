package com.simple.auth.banking.model.entity;

import com.simple.auth.banking.constants.enums.UserRole;
import com.simple.auth.banking.constants.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ServiceUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String customerId;

    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Date createdDate;
    private Date modifiedDate;
}
