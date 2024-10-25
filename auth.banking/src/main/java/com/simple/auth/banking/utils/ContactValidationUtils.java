package com.simple.auth.banking.utils;

import com.simple.auth.banking.exception.InvalidRequestException;

public class ContactValidationUtils {
    private ContactValidationUtils() {
        throw new InvalidRequestException("Invalid request");
    }

    public static boolean mobileCheck(String mobile) {
        String regex = "^\\d{9,10}$";
        if (mobile == null || mobile.isBlank()) {
            return false;
        }
        return mobile.matches(regex);
    }

    public static boolean emailCheck(String email) {
        String regex = "^[a-zA-Z0-9]{5,20}\\@[a-zA-Z0-9]{5,20}\\.[a-zA-Z0-9]{2,5}$";
        if (email == null || email.isBlank()) {
            return false;
        }
        return email.matches(regex);
    }
}
