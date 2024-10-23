package com.simple.auth.banking.model.entity;

import com.simple.auth.banking.constants.enums.TransactionAction;
import com.simple.auth.banking.constants.enums.TransactionType;
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
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountNo;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private TransactionAction transactionAction;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String debitAccount;

    private String creditAccount;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private Date transactionDate;
}
