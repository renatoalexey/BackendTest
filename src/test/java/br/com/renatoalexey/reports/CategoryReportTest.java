package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
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
        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setValue(10);
        accountTransactionDTO.setCategoria(CategoryType.ALIMENTACAO);

        AccountTransactionDTO accountTransactionDTO2 = new AccountTransactionDTO();
        accountTransactionDTO2.setValue(20);
        accountTransactionDTO2.setCategoria(CategoryType.ALIMENTACAO);

        AccountTransactionDTO accountTransactionDTO3 = new AccountTransactionDTO();
        accountTransactionDTO3.setValue(5);
        accountTransactionDTO3.setCategoria(CategoryType.DIVERSAO);

        List<AccountTransactionDTO> accountTransactionDTOList  = Arrays.asList(accountTransactionDTO, accountTransactionDTO2, accountTransactionDTO3);

        for (AccountTransactionDTO transactionDTO : accountTransactionDTOList) {
            categoryReport.buildsReportInformation(transactionDTO);
        }

        Map<CategoryType, Double> paymentsByCategoryMap = categoryReport.getPaymentsByCategoryMap();
        Assert.assertEquals(30.0,  paymentsByCategoryMap.get(CategoryType.ALIMENTACAO), 0.1);
        Assert.assertEquals(5.0,  paymentsByCategoryMap.get(CategoryType.DIVERSAO), 0.1);
    }
}
