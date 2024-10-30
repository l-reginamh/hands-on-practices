package com.simple.auth.banking.model.response;

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
