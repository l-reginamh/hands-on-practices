package com.simple.auth.banking.model.dto;

@ExtendWith(MockitoExtension.class)
class AccountDtoTest {
    @InjectMocks
    private Accountdto accountDto;

    @Test
    void accountDtoTest() {
        accountDto.setId(1L);
        accountDto.setAccountNo(10000001L);
        accountDto.setAccountType(AccountType.SAVING);
        accountDto.setName("Jane Doe");
        accountDto.setCustomerId("910101101001");
        accountDto.setAccountStatus(AccountStatus.ACTIVE);
        accountDto.setTransactStatus(TransactStatus.ACTIVE);
        accountDto.setTransactionLimit(new BigDecimal("3000.00"));
        accountDto.setCardNo("1234123412341234");
        accountDto.setCardStatus(CardStatus.ACTIVE);
        accountDto.setCardExpiry("01/28");
        accountDto.setEncryptedCardNo("************1234");

        assertEqual(1L, accountDto.getId());
        assertEqual(10000001L, accountDto.getAccountNo());
        assertEqual(AccountType.SAVING, accountDto.getAccountType());
        assertEqual("Jane Doe", accountDto.getName());
        assertEqual("910101101001", accountDto.getCustomerId());
        assertEqual(AccountStatus.ACTIVE, accountDto.getAccountStatus());
        assertEqual(TransactStatus.ACTIVE, accountDto.getTransactStatus());
        assertEqual(new BigDecimal("3000.00"), accountDto.getTransactionLimit());
        assertEqual("1234123412341234", accountDto.getCardNo());
        assertEqual(CardStatus.ACTIVE, accountDto.getCardStatus());
        assertEqual("01/28", accountDto.getCardExpiry(());
        assertEqual("************1234", accountDto.getEncryptedCardNo());
    }
}
