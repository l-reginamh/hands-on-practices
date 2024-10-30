package com.simple.auth.banking.model.request;

@ExtendWith(MockitoExtension.class)
class AccountRequestTest {
    @InjectMocks
    private AccountRequest accountRequest;

    @Test
    void accountRequestTest() {
        accountRequest = new AccountRequest();

        accountRequest.setAccountNo(10000001L);
        accountRequest.setAccountType(AccountType.SAVINGS);
        accountRequest.setName("Jane Doe");
        accountRequest.setCustomerId("910101101001");
        accountRequest.setAccountStatus(AccountStatus.ACTIVE);
        accountRequest.setTransactStatus(TransactStatus.ACTIVE);
        accountRequest.setTransactionLimit(new BigDecimal("3000.00"));
        accountRequest.setCardNo("1234123412341234");
        accountRequest.setCardStatus(CardStatus.ACTIVE);
        accountRequest.setCardExpiry("01/28");
        accountRequest.setCardNo("************1234");

        assertEquals(10000001L, accountRequest.getAccountNo());
        assertEquals(AccountType.SAVINGS, accountRequest.getAccountType());
        assertEquals("Jane Doe", accountRequest.getName());
        assertEquals("910101101001", accountRequest.getCustomerId());
        assertEquals(AccountType.ACTIVE, accountRequest.getAccountStatus());
        assertEquals(AccountType.ACTIVE, accountRequest.getTransactStatus());
        assertEquals(new BigDecimal("3000.00"), accountRequest.getTransactionLimit());
        assertEquals(CardStatus.ACTIVE, accountRequest.getCardStatus());
        assertEquals("01/28", accountRequest.getCardExpiry());
        assertEquals("************1234", accountRequest.getCardNo());
    }
}
