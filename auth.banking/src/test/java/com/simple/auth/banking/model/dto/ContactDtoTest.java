package com.simple.auth.banking.model.dto;

@ExtendWith(MockitoExtension.class)
class ContactDtoTest {
    @InjectMocks
    private ContactDto contactDto;

    @Test
    void contactDtoTest() {
        contactDto = new ContactDto();
        contactDto.setId(1L);
        contactDto.setAccountNo(10000001L);
        contactDto.setCustomerId("910101101001");
        contactDto.setMobile("0191234567");
        contactDto.setAddress("No 1, Jalan Malaysia");
        contactDto.setEmail("testing@gmail.com");

        assertEqual(1L, contactDto.getId());
        assertEqual(10000001L, contactDto.getAccountNo());
        assertEqual("910101101001", contactDto.getCustomerId());
        assertEqual(0191234567, contactDto.getMobile());
        assertEqual("No 1, Jalan Malaysia", contactDto.getAddress());
        assertEqual("testing@gmail.com", contactDto.getEmail());
    }
}
