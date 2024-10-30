package com.simple.auth.banking.model.entity;

@ExtendWith(MockitoExtension.class)
class TransactionsTest {
    @InjectMocks
    private Transactions transactions;

    @Test
    void transactionsTest() {
        transactions = new Transactions();
        transactions.setId(1L);
        transactions.setAccountNo(10000001L);
        transactions.setBalance(new BigDecimal("10.00"));
        transactions.setTransactionAction(TransactionAction.DEBIT);
        transactions.setTransactionType(TransactionType.ONLINE);
        transactions.setTransactionStatus(TransactionStatus.CANCELLED);
        transactions.setDebitAccount(10000002L);
        transactions.setCreditAccount(10000001L);
        transactions.setDebitAmount(new BigDecimal("10.00"));
        transactions.setCreditAmount(new BigDecimal("0.00"));
        transactions.setTransactionDate(new Date(Calendar.getInstance().getTimeInMillis()));

        assertEquals(1L, transactions.getId());
        assertEquals(10000001L, transactions.getAccountNo());
        assertEquals(new BigDecimal("10.00"), transactions.getBalance());
        assertEquals(TransactionAction.DEBIT, transactions.getTransactionAction());
        assertEquals(TransactionType.ONLINE, transactions.getTransactionType());
        assertEquals(TransactionStatus.CANCELLED, transactions.getTransactionStatus());
        assertEquals(10000001L, transactions.getDebitAccount());
        assertEquals(10000002L, transactions.getCreditAccount());
        assertEquals(new BigDecimal("10.00"), transactions.getDebitAmount());
        assertEquals(new BigDecimal("0.00"), transactions.getCreditAmount());
        assertNotNull(transactions.getTransactionDate());
    }
}
