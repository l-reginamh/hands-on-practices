package com.simple.auth.banking.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiTokenResponse {
    private StatusResponse statusResponse;
    private String token;
    private Object data;
}
