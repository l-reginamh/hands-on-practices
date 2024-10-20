package com.simple.auth.banking.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatusResponse {
    private String code;
    private String description;
    private String message;
}
