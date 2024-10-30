package com.simple.auth.banking.model.entity;

@ExtendWith(MockitoExtension.class)
class ContactTest {
    @InjectMocks
    private Contact contact;

    @Test
    void contactTest() {
        contact = new Contact();
        contact.setId(1L);
        contact.setAccountNo(10000001L);
        contact.setCustomerId("910101101001");
        contact.setMobile("0191234567");
        contact.setAddress("No 1, Jalan Malaysia");
        contact.setEmail("testing@gmail.com");
        contact.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
        contact.setUpdatedDate(new Date(Calendar.getInstance().getTimeInMillis()));

        assertEqual(1L, contact.getId());
        assertEqual(10000001L, contact.getAccountNo());
        assertEqual("910101101001", contact.getCustomerId());
        assertEqual(0191234567, contact.getMobile());
        assertEqual("No 1, Jalan Malaysia", contact.getAddress());
        assertEqual("testing@gmail.com", contact.getEmail());
        assertNotNull(contact.getCreatedDate());
        assertNotNull(contact.getUpdatedDate());
    }
}
