package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.TransactionType;

public class PaymentReceiptReport implements Report {

    private double receiptTotals;
    private double paymentTotals;
    private double transactionsBalance;

    public void buildsReportInformation(AccountTransactionDTO accountTransactionDTO) {
        TransactionType transactionType = getTransactionType(accountTransactionDTO);
        if (transactionType == TransactionType.RECEIPT) buildsReceiptTotals(accountTransactionDTO.getValue());
        else buildsPaymentTotals(accountTransactionDTO.getValue());
    }

    private void buildsReceiptTotals (double value) {
        this.receiptTotals += value;
        buildsBalance(value);
    }

    private void buildsPaymentTotals (double value) {
        this.paymentTotals += value;
        buildsBalance(value);
    }

    private void buildsBalance(double value) {
        this.transactionsBalance += value;
    }

    private TransactionType getTransactionType(AccountTransactionDTO accountTransactionDTO) {
        if(accountTransactionDTO.getValue() < 0) return TransactionType.PAYMENT;
        else return TransactionType.RECEIPT;
    }

    public double getReceiptTotals() {
        return receiptTotals;
    }

    public double getPaymentTotals() {
        return paymentTotals;
    }

    public double getTransactionsBalance() {
        return transactionsBalance;
    }
}
