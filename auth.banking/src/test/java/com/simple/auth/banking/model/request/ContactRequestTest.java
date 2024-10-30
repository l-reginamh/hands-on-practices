package com.simple.auth.banking.model.request;

@ExtendWith(MockitoExtension.class)
class ContactRequestTest {
    @InjectMocks
    private ContactRequest contactRequest;

    @Test
    void contactRequestTest() {
        contactRequest.setAccountNo(10000001L);
        contactRequest.setCustomerId("910101101001");
        contactRequest.setMobile("0198273829");
        contactRequest.setAddress("No 1, Jalan Malaysia");
        contactRequest.setEmail("testing@gmail.com");

        assertEquals(10000001L, contactRequest.getAccountNo());
        assertEquals("910101101001", contactRequest.getCustomerId());
        assertEquals("0198273829", contactRequest.getMobile());
        assertEquals("No 1, Jalan Malaysia", contactRequest.getAddress());
        assertEquals("testing@gmail.com", contactRequest.getEmail());
    }
}
