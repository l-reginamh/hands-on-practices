package com.simple.auth.banking.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
    private StatusResponse statusResponse;
    private Object data;
}
