package com.simple.auth.banking.utils;

import com.simple.auth.banking.constants.enums.*;
import com.simple.auth.banking.exception.InvalidRequestException;

import java.math.BigDecimal;
import java.sql.Date;

public class InputValidationUtils {
    private InputValidationUtils() {
        throw new InvalidRequestException("Invalid request");
    }

    public static String inputValidation(String originalData, String input) {
        if (input == null || input.isBlank()) {
            return originalData;
        }
        return input;
    }

    public static AccountType inputValidation(AccountType originalData, AccountType input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static AccountStatus inputValidation(AccountStatus originalData, AccountStatus input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static CardStatus inputValidation(CardStatus originalData, CardStatus input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static TransactStatus inputValidation(TransactStatus originalData, TransactStatus input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static TransactionType inputValidation(TransactionType originalData, TransactionType input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static TransactionAction inputValidation(TransactionAction originalData, TransactionAction input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static TransactionStatus inputValidation(TransactionStatus originalData, TransactionStatus input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static Integer inputValidation(Integer originalData, Integer input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static BigDecimal inputValidation(BigDecimal originalData, BigDecimal input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static Date inputValidation(Date originalData, Date input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }

    public static Long inputValidation(Long originalData, Long input) {
        if (input == null) {
            return originalData;
        }
        return input;
    }
}
