package com.simple.auth.banking.model.entity;

@ExtendWith(MockitoExtension.class)
class ServiceUserTest {
    @InjectMocks
    private ServiceUser serviceUser;

    @Test
    void serviceUserTest() {
        serviceUser.setId(1L);
        serviceUser.setUsername("username");
        serviceUser.setPassword("password");
        serviceUser.setCustomerId("910101101001");
        serviceUser.setUserStatus(UserStatus.ACTIVE);
        serviceUser.setRole(UserRole.PERSONAL);
        serviceUser.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
        serviceUser.setUpdatedDate(new Date(Calendar.getInstance().getTimeInMillis()));

        assertEquals(1L, serviceUser.getId());
        assertEquals("username", serviceUser.getUsername());
        assertEquals("password", serviceUser.getPassword());
        assertEquals("910101101001", serviceUser.getCustomerId());
        assertEquals(UserStatus.ACTIVE, serviceUser.getUserStatus());
        assertEquals(UserRole.PERSONAL, serviceUser.getRole());
        assertNotNull(serviceUser.getCreatedDate());
        assertNotNull(serviceUser.getUpdatedDate());
    }
}
