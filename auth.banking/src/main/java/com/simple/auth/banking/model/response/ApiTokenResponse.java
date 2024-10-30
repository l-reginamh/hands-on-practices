package com.simple.auth.banking.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiTokenResponse {
    private StatusResponse statusResponse;
    private String token;
    private Object data;
}
