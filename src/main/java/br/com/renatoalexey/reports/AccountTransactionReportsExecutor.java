package br.com.renatoalexey.reports;

import br.com.renatoalexey.factory.ObjectFactory;
import br.com.renatoalexey.model.AccountTransactionDTO;

import java.util.*;

public class AccountTransactionReportsExecutor {

    private List<AccountTransactionDTO> accountTransactionDTOList;
    private List<Report> reportList;

    public AccountTransactionReportsExecutor(ObjectFactory objectFactory) {
        reportList = Arrays.asList(objectFactory.getCategoryReport(), objectFactory.getPaymentReceiptReport(), objectFactory.getMostExpensiveMonthReport());
        accountTransactionDTOList = new ArrayList<>();
    }

    public void buildsAllReportsData(AccountTransactionDTO accountTransactionDTO) {
        accountTransactionDTOList.add(accountTransactionDTO);
        for (Report report : reportList) {
            report.buildsReportInformation(accountTransactionDTO);
        }
    }

    public void printsAllReportsData() {
        Collections.sort(accountTransactionDTOList, Comparator.comparing(AccountTransactionDTO::getDate));
        System.out.println("Log das movimentações ordenadas por data:");
        for (AccountTransactionDTO accountTransactionDTO : accountTransactionDTOList) {
            System.out.println(accountTransactionDTO);
        }
        for (Report report : reportList) {
            report.printsReport();
        }
    }

    public List<AccountTransactionDTO> getAccountTransactionDTOList() {
        return accountTransactionDTOList;
    }
}
