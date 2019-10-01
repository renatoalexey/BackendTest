package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for creating two reports:
 *  1 - Total cost of each category
 *  2 - Most expensive category
 *  @see Report
 *  @author Renato Alexey
 */
public class CategoryReport implements Report{
    private Map<CategoryType, Double> paymentsByCategoryMap = new HashMap<CategoryType, Double>();
    private CategoryType mostExpensiveCategory = null;
    private double mostExpensiveCategoryValue = 0;

    @Override
    public void buildsReportInformation(AccountTransactionDTO accountTransactionDTO) {
        Pair<Double, CategoryType> doubleCategoryTypePair = buildPaymentsByCategory(accountTransactionDTO);
        buildsMostExpensiveCategory(doubleCategoryTypePair.getLeft(), doubleCategoryTypePair.getRight());
    }

    @Override
    public void printsReport() {

    }

    private Pair<Double, CategoryType> buildPaymentsByCategory(AccountTransactionDTO accountTransactionDTO) {

        Double totalByCategory = paymentsByCategoryMap.get(accountTransactionDTO.getCategoria());
        if(totalByCategory == null) {
            totalByCategory = new Double(0);
            paymentsByCategoryMap.put(accountTransactionDTO.getCategoria(), totalByCategory);
        }
        totalByCategory += accountTransactionDTO.getValue();

        return Pair.of(totalByCategory, accountTransactionDTO.getCategoria());
    }

    private void buildsMostExpensiveCategory(Double totalByCategory, CategoryType categoryType) {
        if(totalByCategory < mostExpensiveCategoryValue) mostExpensiveCategory = categoryType;
    }

    public Map<CategoryType, Double> getPaymentsByCategoryMap() {
        return paymentsByCategoryMap;
    }

    public CategoryType getMostExpensiveCategory() {
        return mostExpensiveCategory;
    }

}
