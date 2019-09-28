package br.com.renatoalexey.manager;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import br.com.renatoalexey.model.TransactionType;
import br.com.renatoalexey.reports.CategoryReport;
import br.com.renatoalexey.reports.PaymentReceiptReport;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

public class AccountTransactionManager {

    @Autowired
    private CategoryReport categoryReport;

    @Autowired
    private PaymentReceiptReport paymentReceiptReport;

    public Map<TransactionType, List<AccountTransactionDTO>> getMap(List<AccountTransactionDTO> listFile,
                                                List<AccountTransactionDTO> listJson) {

        List<AccountTransactionDTO> sortedList = sortList(listFile, listJson);

        Map<TransactionType, List<AccountTransactionDTO>> transactionTypeListMap =
                new LinkedHashMap<TransactionType, List<AccountTransactionDTO>>();

        for(AccountTransactionDTO accountTransactionDTO : sortedList) {
            categoryReport.buildsReportInformation(accountTransactionDTO);
            paymentReceiptReport.buildsReportInformation(accountTransactionDTO);

            TransactionType transactionType = getTransactionType(accountTransactionDTO);

            List<AccountTransactionDTO> listinha = transactionTypeListMap.get(transactionType);
            if(listinha == null) listinha = new ArrayList<AccountTransactionDTO>();
            listinha.add(accountTransactionDTO);
            transactionTypeListMap.put(transactionType, listinha);
        }

        return transactionTypeListMap;
    }

    public double sumAllReceipt (Map<TransactionType, List<AccountTransactionDTO>> transactionTypeListMap) {
       return sumAllTransactionsByTpe(transactionTypeListMap, TransactionType.RECEIPT);
    }

    public double sumAllPayment (Map<TransactionType, List<AccountTransactionDTO>> transactionTypeListMap) {

        return sumAllTransactionsByTpe(transactionTypeListMap, TransactionType.PAYMENT);
    }

    public Map<CategoryType, Double> getExpensesByCategory(List<AccountTransactionDTO> accountTransactionDTOList ) {
        double maxCategoryValue = 0;
        CategoryType maxCategory = null;

        Map<CategoryType, Double> mapinha = new HashMap<CategoryType, Double>();

        for(AccountTransactionDTO accountTransactionDTO : accountTransactionDTOList) {
            Double totalByCategory = mapinha.get(accountTransactionDTO.getCategoria());
            if(totalByCategory == null) {
                totalByCategory = new Double(0);
                mapinha.put(accountTransactionDTO.getCategoria(), totalByCategory);
            }
            totalByCategory += accountTransactionDTO.getValue();

            if(totalByCategory < maxCategoryValue) maxCategory = accountTransactionDTO.getCategoria();
        }

        return mapinha;
    }

    public CategoryType getCategoryMostExpense(Map<CategoryType, Double> categoryTypeDoubleMap) {
        return null;
    }

    private double sumAllTransactionsByTpe (Map<TransactionType, List<AccountTransactionDTO>> transactionTypeListMap,
                                            TransactionType transactionType) {
        double totals = 0;

        for(AccountTransactionDTO accountTransactionDTO : transactionTypeListMap.get(transactionType)) {
            totals += accountTransactionDTO.getValue();
        }

        return totals;
    }

    private List<AccountTransactionDTO> sortList(List<AccountTransactionDTO> listFile, List<AccountTransactionDTO> listJson) {
        List<AccountTransactionDTO> sortedList = new ArrayList<AccountTransactionDTO>(listFile);
        sortedList.addAll(listJson);

        Collections.sort(sortedList, new Comparator<AccountTransactionDTO>() {
            public int compare(AccountTransactionDTO o1, AccountTransactionDTO o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        return sortedList;
    }

    private TransactionType getTransactionType(AccountTransactionDTO accountTransactionDTO) {
        if(accountTransactionDTO.getValue() < 0) return TransactionType.PAYMENT;
        else return TransactionType.RECEIPT;
    }

    public String getMostExpensiveMonth(List<AccountTransactionDTO> accountTransactionDTOList) {
        double maxMonthValue = 0;
        String maxMonth = "";
        Map<String, Double> mapinha = new HashMap<String, Double>();
        for(AccountTransactionDTO accountTransactionDTO : accountTransactionDTOList) {
            String month = getMonthByDate(accountTransactionDTO.getDate());

            Double monthValue =  mapinha.get(month);
            if(monthValue == null) {
                monthValue = new Double(0);
                mapinha.put(month, monthValue);
            }

            monthValue += accountTransactionDTO.getValue();

            if(monthValue < maxMonthValue) maxMonth = month;

        }
        return  maxMonth;
    }

    private String getMonthByDate(Date date) {

        return new SimpleDateFormat("MMMM").format(date);
    }
}
