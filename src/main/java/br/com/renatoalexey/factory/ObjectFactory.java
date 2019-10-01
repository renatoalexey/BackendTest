package br.com.renatoalexey.factory;

import br.com.renatoalexey.connect.ConnectsToJsonAPI;
import br.com.renatoalexey.reader.AccountTransactionReaderFromFile;
import br.com.renatoalexey.reader.AccountTransactionReaderFromJson;
import br.com.renatoalexey.reports.AccountTransactionReportsExecutor;
import br.com.renatoalexey.reports.CategoryReport;
import br.com.renatoalexey.reports.MostExpensiveMonthReport;
import br.com.renatoalexey.reports.PaymentReceiptReport;

public class ObjectFactory {

    private static ObjectFactory objectFactory;

    private ObjectFactory() {

    }

    public static synchronized ObjectFactory getInstance() {
        if(objectFactory == null) objectFactory = new ObjectFactory();
        return objectFactory;
    }

    public CategoryReport getCategoryReport() {
        return new CategoryReport();
    }

    public MostExpensiveMonthReport getMostExpensiveMonthReport() {
        return new MostExpensiveMonthReport();
    }

    public PaymentReceiptReport getPaymentReceiptReport() {
        return new PaymentReceiptReport();
    }

    public AccountTransactionReaderFromJson getAccountTransactionReaderFromJson() {
        return new AccountTransactionReaderFromJson(getConnectsToJsonAPI());
    }

    public ConnectsToJsonAPI getConnectsToJsonAPI() {
        return new ConnectsToJsonAPI();
    }

    public AccountTransactionReaderFromFile getAccountTransactionReaderFromFile() {
        return new AccountTransactionReaderFromFile();
    }

    public AccountTransactionReportsExecutor getAccountTransactionReportsExecutor(){
        return new AccountTransactionReportsExecutor(ObjectFactory.getInstance());
    }

}
