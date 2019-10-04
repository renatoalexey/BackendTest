package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CategoryReportTest {

    private CategoryReport categoryReport;

    @Before
    public void setup() {
        categoryReport = new CategoryReport();
    }

    @Test
    public void testGeneratorOfMapCategoryCost() {
        List<AccountTransactionDTO> accountTransactionDTOList = buildListForTest();

        for (AccountTransactionDTO transactionDTO : accountTransactionDTOList) {
            categoryReport.buildsReportInformation(transactionDTO);
        }

        Map<CategoryType, Double> paymentsByCategoryMap = categoryReport.getPaymentsByCategoryMap();
        Assert.assertEquals(-30.0,  paymentsByCategoryMap.get(CategoryType.ALIMENTACAO), 0.1);
        Assert.assertEquals(-5.0,  paymentsByCategoryMap.get(CategoryType.DIVERSAO), 0.1);
        Assert.assertEquals(CategoryType.ALIMENTACAO, categoryReport.getMostExpensiveCategory());
    }

    private List<AccountTransactionDTO> buildListForTest() {
        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setValue(-10);
        accountTransactionDTO.setCategoryType(CategoryType.ALIMENTACAO);

        AccountTransactionDTO accountTransactionDTO2 = new AccountTransactionDTO();
        accountTransactionDTO2.setValue(-20);
        accountTransactionDTO2.setCategoryType(CategoryType.ALIMENTACAO);

        AccountTransactionDTO accountTransactionDTO3 = new AccountTransactionDTO();
        accountTransactionDTO3.setValue(-5);
        accountTransactionDTO3.setCategoryType(CategoryType.DIVERSAO);

        return Arrays.asList(accountTransactionDTO, accountTransactionDTO2, accountTransactionDTO3);
    }
}
