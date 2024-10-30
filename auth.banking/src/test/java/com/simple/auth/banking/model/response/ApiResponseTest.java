package com.simple.auth.banking.model.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ApiResponseTest {
    @InjectMocks
    private ApiResponse apiResponse;

    @Test
    void apiResponseTest() {
        apiResponse = new ApiResponse();
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode("200");
        apiResponse.setStatusResponse(statusResponse);
        apiResponse.setData("sample data");

        assertEquals("200", apiResponse.getStatusResponse().getCode());
        assertEquals("sample data", apiResponse.getData());
    }
}
