package com.simple.auth.banking.constants;


import com.simple.auth.banking.exception.InvalidRequestException;

public class MessageConstants {
    private MessageConstants() {
        throw new InvalidRequestException("Invalid request");
    }
    public static final String SUCCESS = "Success";
    public static final String USER_CREATED = "User created successfully.";
    public static final String USER_LOGGED_IN = "User logged in.";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String VALIDATION_FAILED = "Validation failed";
    public static final String INVALID_CREDENTIAL = "Invalid username or password.";
    public static final String USER_LOCKED = "User has been locked, please contact customer service.";
    public static final String USER_CLOSED = "This User is no longer available.";
    public static final String USER_NOT_FOUND = "User not found!";
    public static final String USER_ALREADY_EXIST = "Username taken, please try again.";
}
