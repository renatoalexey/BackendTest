package br.com.renatoalexey.model;

public enum TransactionType {
    PAYMENT,
    RECEIPT;

    public static TransactionType getTransactionType(AccountTransactionDTO accountTransactionDTO) {
        if(accountTransactionDTO.getValue() < 0) return TransactionType.PAYMENT;
        else return TransactionType.RECEIPT;
    }
}
