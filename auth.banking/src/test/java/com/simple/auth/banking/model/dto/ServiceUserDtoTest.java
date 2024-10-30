package com.simple.auth.banking.model.dto;

@ExtendWith(MockitoExtension.class)
class ServiceUserDtoTest {
    @InjectMocks
    private ServiceUserDto serviceUserDto;

    @Test
    void serviceUserDtoTest() {
        serviceUserDto = new ServiceUserDto();
        serviceUserDto.setId(1L);
        serviceUserDto.setUsername("username");
        serviceUserDto.setPassword("password");
        serviceUserDto.setCustomerId("910101101001");
        serviceUserDto.setUserStatus(UserStatus.ACTIVE);
        serviceUserDto.setRole(UserRole.PERSONAL);

        assertEquals(1L, serviceUserDto.getId());
        assertEquals("username", serviceUserDto.getUsername());
        assertEquals("password", serviceUserDto.getPassword());
        assertEquals("910101101001", serviceUserDto.getCustomerId());
        assertEquals(UserStatus.ACTIVE, serviceUserDto.getUserStatus());
        assertEquals(UserRole.PERSONAL, serviceUserDto.getRole());
    }
}
