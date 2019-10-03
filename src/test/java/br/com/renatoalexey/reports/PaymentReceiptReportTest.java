package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class PaymentReceiptReportTest {

    private PaymentReceiptReport paymentReceiptReport;

    @Before
    public void setup() {
        paymentReceiptReport = new PaymentReceiptReport();
    }

    @Test
    public void testPaymentReceiptReport(){
        double balanceExpected = 12.0;
        double paymentTotalaExpected = -38.0;
        double receiptTotalExpected = 50.0;

        List<AccountTransactionDTO> accountTransactionDTOList = buildListForTest();
        for (AccountTransactionDTO accountTransactionDTO : accountTransactionDTOList) {
            paymentReceiptReport.buildsReportInformation(accountTransactionDTO);
        }

        Assert.assertEquals(balanceExpected, paymentReceiptReport.getTransactionsBalance(), 0.1);
        Assert.assertEquals(paymentTotalaExpected, paymentReceiptReport.getPaymentTotals(), 0.1);
        Assert.assertEquals(receiptTotalExpected, paymentReceiptReport.getReceiptTotals(), 0.1);
    }

    private List<AccountTransactionDTO> buildListForTest() {

        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setValue(-10);

        AccountTransactionDTO accountTransactionDTO2 = new AccountTransactionDTO();
        accountTransactionDTO2.setValue(-20);

        AccountTransactionDTO accountTransactionDTO3 = new AccountTransactionDTO();
        accountTransactionDTO3.setValue(50);

        AccountTransactionDTO accountTransactionDTO4 = new AccountTransactionDTO();
        accountTransactionDTO4.setValue(-8);

        return Arrays.asList(accountTransactionDTO, accountTransactionDTO2, accountTransactionDTO3, accountTransactionDTO4);
    }
}
