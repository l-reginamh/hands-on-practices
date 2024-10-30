package com.simple.auth.banking.model.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ApiTokenResponseTest {
    @InjectMocks
    private ApiTokenResponse apiTokenResponse;

    @Test
    void apiTokenResponseTest() {
        apiTokenResponse = new ApiTokenResponse();
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode("200");
        apiTokenResponse.setStatusResponse(statusResponse);
        apiTokenResponse.setToken("d9298r3y9r32e3yhd9fd920ujhdnc20-1iej0n2d0d");
        apiTokenResponse.setData("sample data");

        assertEquals("200", apiTokenResponse.getStatusResponse().getCode());
        assertEquals("d9298r3y9r32e3yhd9fd920ujhdnc20-1iej0n2d0d", apiTokenResponse.getToken());
        assertEquals("sample data", apiTokenResponse.getData());
    }
}
