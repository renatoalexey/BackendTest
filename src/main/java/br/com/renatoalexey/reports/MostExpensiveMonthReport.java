package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.TransactionType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MostExpensiveMonthReport implements Report {
    private Map<String, Double> paymentsByMonthMap = new HashMap<String, Double>();
    private Double mostExpensiveMonthValue = new Double(0);
    private String mostExpensiveMonth = "";

    public void buildsReportInformation(AccountTransactionDTO accountTransactionDTO) {
        TransactionType transactionType = TransactionType.getTransactionType(accountTransactionDTO);
        if(transactionType == TransactionType.PAYMENT) {
            String month = getMonthByDate(accountTransactionDTO.getDate());

            Double monthValue =  paymentsByMonthMap.get(month);
            if(monthValue == null) {
                monthValue = new Double(0);
                paymentsByMonthMap.put(month, monthValue);
            }

            monthValue += accountTransactionDTO.getValue();

            if(monthValue > mostExpensiveMonthValue) {
                mostExpensiveMonth = month;
                mostExpensiveMonthValue = monthValue;
            }
        }
    }

    private String getMonthByDate(Date date) {
        return new SimpleDateFormat("MMMM").format(date);
    }

    public String getMostExpensiveMonth() {
        return mostExpensiveMonth;
    }
}