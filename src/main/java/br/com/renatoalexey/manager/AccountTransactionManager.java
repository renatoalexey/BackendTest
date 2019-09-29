package br.com.renatoalexey.manager;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.TransactionType;
import br.com.renatoalexey.reports.CategoryReport;
import br.com.renatoalexey.reports.MostExpensiveMonthReport;
import br.com.renatoalexey.reports.PaymentReceiptReport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class AccountTransactionManager {

    @Autowired
    private CategoryReport categoryReport;

    @Autowired
    private PaymentReceiptReport paymentReceiptReport;

    @Autowired
    private MostExpensiveMonthReport mostExpensiveMonthReport;

    public Map<TransactionType, List<AccountTransactionDTO>> getMap(List<AccountTransactionDTO> listFile,
                                                                    List<AccountTransactionDTO> listJson) {

        List<AccountTransactionDTO> sortedList = sortList(listFile, listJson);

        for (AccountTransactionDTO accountTransactionDTO : sortedList) {
            categoryReport.buildsReportInformation(accountTransactionDTO);
            paymentReceiptReport.buildsReportInformation(accountTransactionDTO);
            mostExpensiveMonthReport.buildsReportInformation(accountTransactionDTO);
        }

        return null;
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
}
