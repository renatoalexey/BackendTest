package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MostExpensiveMonthReportTest {

    private MostExpensiveMonthReport mostExpensiveMonthReport;

    @Before
    public void setup() {
        mostExpensiveMonthReport = new MostExpensiveMonthReport();
    }

    @Test
    public void testMostExpensiveMonthReport() throws ParseException {
        Double mostExpensiveMonthValueExpected = new Double(-30);
        String mostExpensiveMonthExpected = "Agosto";

        List<AccountTransactionDTO> accountTransactionDTOList = buildListForTestWithDifferentLocalesDorDate();
        for (AccountTransactionDTO accountTransactionDTO : accountTransactionDTOList) {
            mostExpensiveMonthReport.buildsReportInformation(accountTransactionDTO);
        }

        Assert.assertEquals(mostExpensiveMonthExpected, mostExpensiveMonthReport.getMostExpensiveMonth());
        Assert.assertEquals(mostExpensiveMonthValueExpected, mostExpensiveMonthReport.getMostExpensiveMonthValue(), 0.1);
    }


    private List<AccountTransactionDTO> buildListForTestWithDifferentLocalesDorDate() throws ParseException {
        SimpleDateFormat simpleDateFormatUS = new SimpleDateFormat("dd-MMM", Locale.US);
        SimpleDateFormat simpleDateFormatBR = new SimpleDateFormat("dd-MMM");

        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setValue(-10);
        accountTransactionDTO.setDate(simpleDateFormatUS.parse("01-Aug"));

        AccountTransactionDTO accountTransactionDTO2 = new AccountTransactionDTO();
        accountTransactionDTO2.setValue(-20);
        accountTransactionDTO2.setDate(simpleDateFormatBR.parse("01-Ago"));

        AccountTransactionDTO accountTransactionDTO3 = new AccountTransactionDTO();
        accountTransactionDTO3.setValue(-15);
        accountTransactionDTO3.setDate(simpleDateFormatBR.parse("01-Set"));

        AccountTransactionDTO accountTransactionDTO4 = new AccountTransactionDTO();
        accountTransactionDTO4.setValue(-8);
        accountTransactionDTO4.setDate(simpleDateFormatBR.parse("20-Set"));

        return Arrays.asList(accountTransactionDTO, accountTransactionDTO2, accountTransactionDTO3, accountTransactionDTO4);
    }
}
