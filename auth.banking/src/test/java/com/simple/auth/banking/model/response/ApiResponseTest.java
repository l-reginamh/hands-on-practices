package com.simple.auth.banking.model.response;

@ExtendWith(MockitoExtension.class)
class ApiResponseTest {
    @InjectMocks
    private ApiResponse apiResponse;

    @Test
    void apiResponseTest() {
        apiResponse = new ApiResponse;
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode("200");
        apiResponse.setStatusResponse(statusResponse);
        apiResponse.setData("sample data");

        assertEquals("200", apiResponse.getStatusResponse().getCode());
        assertEquals("sample data", apiResponse.getData());
    }
}
