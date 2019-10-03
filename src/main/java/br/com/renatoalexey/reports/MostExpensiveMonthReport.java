package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.TransactionType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that calculates the most expensive month
 *  @see Report
 *  @author Renato Alexey
 */
public class MostExpensiveMonthReport implements Report {
    private Map<String, Double> paymentsByMonthMap = new HashMap<>();
    private Double mostExpensiveMonthValue = new Double(0);
    private String mostExpensiveMonth = "";

    @Override
    public void buildsReportInformation(AccountTransactionDTO accountTransactionDTO) {
        TransactionType transactionType = TransactionType.getTransactionType(accountTransactionDTO);
        if(transactionType == TransactionType.PAYMENT) {
            String month = getMonthByDate(accountTransactionDTO.getDate());

            Double monthValue =  paymentsByMonthMap.get(month);
            if(monthValue == null) {
                monthValue = new Double(0);
            }

            monthValue += accountTransactionDTO.getValue();
            paymentsByMonthMap.put(month, monthValue);

            if(monthValue < mostExpensiveMonthValue) {
                mostExpensiveMonth = month;
                mostExpensiveMonthValue = monthValue;
            }
        }
    }

    @Override
    public void printsReport() {
        System.out.println("Mês de maior gasto: " + mostExpensiveMonth + " Gasto: " + mostExpensiveMonthValue);
    }

    private String getMonthByDate(Date date) {
        return new SimpleDateFormat("MMMM").format(date);
    }

    public String getMostExpensiveMonth() {
        return mostExpensiveMonth;
    }
}
