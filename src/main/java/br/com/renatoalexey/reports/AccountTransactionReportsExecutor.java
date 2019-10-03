package br.com.renatoalexey.reports;

import br.com.renatoalexey.factory.ObjectFactory;
import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.reader.AccountTransactionReaderFromFile;
import br.com.renatoalexey.reader.AccountTransactionReaderFromJson;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class AccountTransactionReportsExecutor {

    private final String JSON_API = "https://my-json-server.typicode.com/cairano/backend-test/db";

    private AccountTransactionReaderFromFile accountTransactionReaderFromFile;

    private AccountTransactionReaderFromJson accountTransactionReaderFromJson;

    private List<AccountTransactionDTO> allTransactionsSortedByDate;

    private List<Report> reportList;

    public AccountTransactionReportsExecutor(ObjectFactory objectFactory) {
        accountTransactionReaderFromFile = objectFactory.getAccountTransactionReaderFromFile();
        accountTransactionReaderFromJson = objectFactory.getAccountTransactionReaderFromJson();

        reportList = Arrays.asList(objectFactory.getCategoryReport(), objectFactory.getPaymentReceiptReport(), objectFactory.getMostExpensiveMonthReport());
    }

    public void execute(String filePath) throws IOException, ParseException {
        List<AccountTransactionDTO> listAccountTransactionDTOFromFile = accountTransactionReaderFromFile.transformsFileIntoDTO(filePath);
        List<AccountTransactionDTO> listAccountTransactionDTOFromJson = accountTransactionReaderFromJson.transformsJsonIntoDTO(JSON_API);

        allTransactionsSortedByDate = generatesSingleListSortedByDate(listAccountTransactionDTOFromFile, listAccountTransactionDTOFromJson);

        buildsAllReportsData();
        printsAllReportsData();

    }

    private void buildsAllReportsData() {
        for (AccountTransactionDTO accountTransactionDTO : allTransactionsSortedByDate) {
            for (Report report : reportList) {
                report.buildsReportInformation(accountTransactionDTO);
            }
        }

    }

    private void printsAllReportsData() {
        for (Report report : reportList) {
            report.printsReport();
        }
    }

    private List<AccountTransactionDTO> generatesSingleListSortedByDate(List<AccountTransactionDTO> listFile, List<AccountTransactionDTO> listJson) {
        List<AccountTransactionDTO> sortedList = new ArrayList<AccountTransactionDTO>(listFile);
        sortedList.addAll(listJson);

        Collections.sort(sortedList, Comparator.comparing(AccountTransactionDTO::getDate));

        return sortedList;
    }

}
