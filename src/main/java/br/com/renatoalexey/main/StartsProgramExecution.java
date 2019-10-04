package br.com.renatoalexey.main;

import br.com.renatoalexey.factory.ObjectFactory;
import br.com.renatoalexey.reader.AccountTransactionReaderFromFile;
import br.com.renatoalexey.reader.AccountTransactionReaderFromJson;
import br.com.renatoalexey.reports.AccountTransactionReportsExecutor;

import java.io.IOException;
import java.text.ParseException;

public class StartsProgramExecution {
    private final String JSON_API = "https://my-json-server.typicode.com/cairano/backend-test/db";

    private AccountTransactionReaderFromFile accountTransactionReaderFromFile;
    private AccountTransactionReaderFromJson accountTransactionReaderFromJson;
    private AccountTransactionReportsExecutor accountTransactionReportsExecutor;

    public StartsProgramExecution() {
        ObjectFactory objectFactory = ObjectFactory.getInstance();
        accountTransactionReportsExecutor = objectFactory.getAccountTransactionReportsExecutor();
        accountTransactionReaderFromFile = objectFactory.getAccountTransactionReaderFromFile(accountTransactionReportsExecutor);
        accountTransactionReaderFromJson = objectFactory.getAccountTransactionReaderFromJson(accountTransactionReportsExecutor);
    }

    public void run(String filePath) throws IOException, ParseException {
        accountTransactionReaderFromFile.generatesReportsFromFile(filePath);
        accountTransactionReaderFromJson.generatesReportsFromJSON(JSON_API);

        accountTransactionReportsExecutor.printsAllReportsData();
    }
}
