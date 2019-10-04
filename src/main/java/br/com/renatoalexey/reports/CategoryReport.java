package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import br.com.renatoalexey.model.TransactionType;
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
        if(TransactionType.getTransactionType(accountTransactionDTO) == TransactionType.RECEIPT) return;
        Pair<Double, CategoryType> doubleCategoryTypePair = buildPaymentsByCategory(accountTransactionDTO);
        buildsMostExpensiveCategory(doubleCategoryTypePair.getLeft(), doubleCategoryTypePair.getRight());
    }

    @Override
    public void printsReport() {
        System.out.println("Gasto total por Categoria:");
        for (Map.Entry<CategoryType, Double> categoryTypeDoubleEntry : paymentsByCategoryMap.entrySet()) {
            CategoryType categoryType = categoryTypeDoubleEntry.getKey();
            if(categoryType != null)
                System.out.println("Categoria: " + categoryType.getCategoryName() + "  Gasto Total: " + categoryTypeDoubleEntry.getValue());
        }

        System.out.println("Categoria de maior gasto: " + mostExpensiveCategory.getCategoryName());
    }

    private Pair<Double, CategoryType> buildPaymentsByCategory(AccountTransactionDTO accountTransactionDTO) {

        Double totalByCategory = paymentsByCategoryMap.get(accountTransactionDTO.getCategoryType());
        if(totalByCategory == null) {
            totalByCategory = new Double(0);
        }
        totalByCategory += accountTransactionDTO.getValue();
        paymentsByCategoryMap.put(accountTransactionDTO.getCategoryType(), totalByCategory);

        return Pair.of(totalByCategory, accountTransactionDTO.getCategoryType());
    }

    private void buildsMostExpensiveCategory(Double totalByCategory, CategoryType categoryType) {
        if(totalByCategory < mostExpensiveCategoryValue) {
            mostExpensiveCategory = categoryType;
            mostExpensiveCategoryValue = totalByCategory;
        }
    }

    public Map<CategoryType, Double> getPaymentsByCategoryMap() {
        return paymentsByCategoryMap;
    }

    public CategoryType getMostExpensiveCategory() {
        return mostExpensiveCategory;
    }

}
