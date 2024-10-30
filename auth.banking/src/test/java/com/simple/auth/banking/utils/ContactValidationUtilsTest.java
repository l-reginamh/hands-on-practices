package com.simple.auth.banking.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ContactValidationUtilsTest {

    @Test
    void mobileCheck_resultTrue() {
        boolean result = ContactValidationUtils.mobileCheck("1234567890");
        assertTrue(result);
    }

    @Test
    void mobileCheck_resultFalse() {
        boolean result = ContactValidationUtils.mobileCheck("12345678");
        assertFalse(result);
    }

    @Test
    void emailCheck_resultTrue() {
        boolean result = ContactValidationUtils.emailCheck("sample@example.com");
        assertTrue(result);
    }

    @Test
    void emailCheck_resultFalse() {
        boolean result = ContactValidationUtils.emailCheck("sampleexample.com");
        assertFalse(result);
    }
}
