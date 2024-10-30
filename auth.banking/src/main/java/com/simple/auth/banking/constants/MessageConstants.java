package com.simple.auth.banking.constants;


import com.simple.auth.banking.exception.InvalidRequestException;

public class MessageConstants {
    private MessageConstants() {
        throw new InvalidRequestException("Invalid request");
    }

    // Controller Messages
    public static final String SUCCESS = "Success";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String VALIDATION_FAILED = "Validation failed";

    // ServiceUser Service Messages
    public static final String USER_NOT_FOUND = "No user found!";
    public static final String USER_NOT_REGISTERED = "Please register before applying for account.";
    public static final String USER_ACCOUNT_APPLIED = "User has applied for this product.";
    public static final String USER_ACCOUNT_NOT_AVAILABLE = "Account provided not available to proceed with this request";
    public static final String USER_NOT_AVAILABLE = "Invalid customer or account number, please check again or contact customer service.";
    public static final String USER_ALREADY_EXIST = "Customer exist, please proceed to login.";
    public static final String USER_USERNAME_TAKEN = "Username taken.";
    public static final String USER_CREATED = "User created successfully.";
    public static final String USER_LOGGED_IN = "User logged in.";
    public static final String USER_LOCKED = "User has been locked, please contact customer service.";
    public static final String USER_CLOSED = "This User is no longer available.";
    public static final String USER_CLOSED_MESSAGE = "User account has been closed, please contact customer service.";
    public static final String USER_INVALID_STATUS_UPDATE = "Invalid status update request.";
    
    public static final String INVALID_CREDENTIAL = "Invalid username or password.";
    
    public static final String ACCOUNT_NOT_FOUND = "No account found!";

    public static final String CONTACT_NOT_FOUND = "No contact found!";
    public static final String CONTACT_INVALID_EMAIL = "Invalid email.";
    public static final String CONTACT_INVALID_MOBILE = "Invalid mobile.";

    public static final String TRANSACTION_NOT_FOUND = "No transaction found!";
    public static final String TRANSACTION_INVALID_TRANSACT = "Invalid transact status.";
    public static final String TRANSACTION_EXCEED_LIMIT = "Exceed transaction limit.";
    public static final String TRANSACTION_INSUFFICIENT_BALANCE = "Insufficient balance.";
    public static final String TRANSACTION_INVALID = "Invalid transaction";
}
