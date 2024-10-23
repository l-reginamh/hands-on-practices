package com.simple.auth.banking.model.entity;

import com.simple.auth.banking.constants.enums.AccountStatus;
import com.simple.auth.banking.constants.enums.AccountType;
import com.simple.auth.banking.constants.enums.CardStatus;
import com.simple.auth.banking.constants.enums.TransactStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountNo;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String name;

    private String customerId;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private TransactStatus transactStatus;

    private BigDecimal transactionLimit;

    private String cardNo;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    private Date cardExpiry;

    private String encryptedCardNo;

    private Date createdDate;

    private Date modifiedDate;
}
