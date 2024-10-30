package com.simple.auth.banking.model.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StatusResponseTest {
    @InjectMocks
    private StatusResponse statusResponse;

    @Test
    void statusResponseTest() {
        statusResponse = new StatusResponse();
        statusResponse.setCode("200");
        statusResponse.setDescription("Success");
        statusResponse.setMessage("Request processed successfully.");

        assertEquals("200", statusResponse.getCode());
        assertEquals("Success", statusResponse.getDescription());
        assertEquals("Request processed successfully.", statusResponse.getMessage());
    }
}
