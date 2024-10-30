package com.simple.auth.banking.model.entity;

@ExtendWith(MockitoExtension.class)
class AccountTest {
    @InjectMocks
    private Account account;

    @Test
    void accountTest() {
        account.setId(1L);
        account.setAccountNo(10000001L);
        account.setAccountType(AccountType.SAVING);
        account.setName("Jane Doe");
        account.setCustomerId("910101101001");
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setTransactStatus(TransactStatus.ACTIVE);
        account.setTransactionLimit(new BigDecimal("3000.00"));
        account.setCardNo("1234123412341234");
        account.setCardStatus(CardStatus.ACTIVE);
        account.setCardExpiry("01/28");
        account.setEncryptedCardNo("************1234");
        account.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
        account.setUpdatedDate(new Date(Calendar.getInstance().getTimeInMillis()));

        assertEqual(1L, account.getId());
        assertEqual(10000001L, account.getAccountNo());
        assertEqual(AccountType.SAVING, account.getAccountType());
        assertEqual("Jane Doe", account.getName());
        assertEqual("910101101001", account.getCustomerId());
        assertEqual(AccountStatus.ACTIVE, account.getAccountStatus());
        assertEqual(TransactStatus.ACTIVE, account.getTransactStatus());
        assertEqual(new BigDecimal("3000.00"), account.getTransactionLimit());
        assertEqual("1234123412341234", account.getCardNo());
        assertEqual(CardStatus.ACTIVE, account.getCardStatus());
        assertEqual("01/28", account.getCardExpiry(());
        assertEqual("************1234", account.getEncryptedCardNo());
        assertNotNull(account.getCreatedDate());
        assertNotNull(account.getUpdatedDate());
    }
}
