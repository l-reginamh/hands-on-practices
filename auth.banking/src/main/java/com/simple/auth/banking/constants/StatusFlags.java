package com.simple.auth.banking.constants;


import com.simple.auth.banking.exception.InvalidRequestException;

public class StatusFlags {
    private StatusFlags() {
        throw new InvalidRequestException("Invalid request");
    }
    public static final String SUCCESS = "200";
    public static final String BAD_REQUEST = "400";
    public static final String VALIDATION_FAILED = "4000";
    public static final String USER_NOT_FOUND = "4001";
    public static final String USER_ALREADY_EXIST = "4002";
    public static final String USER_INVALID_REQUEST = "4003";
    public static final String USER_CLOSED = "4004";
    public static final String USER_LOCKED = "4005";

}
