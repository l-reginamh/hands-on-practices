package com.simple.auth.banking.constants;

public class DefaultValuesConstants {
    private DefaultValuesConstants() {
        throw new InvalidRequestException("Invalid request");
    }
    public static final AccountStatus DEFAULT_ACCOUNT_STATUS = AccountStatus.ACTIVE;
    public static final TransactStatus DEFAULT_TRANSACT_STATUS = TransactStatus.ACTIVE;
    public static final CardStatus DEFAULT_CARD_STATUS = CardStatus.ACTIVE;
    public static final BigDecimal DEFAULT_TRANSACTION_LIMIT = new BigDecimal("5000.00");
}
