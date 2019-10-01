package br.com.renatoalexey.main;

import br.com.renatoalexey.factory.ObjectFactory;
import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.reader.AccountTransactionReaderFromFile;
import br.com.renatoalexey.reports.AccountTransactionReportsExecutor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            ObjectFactory objectFactory = ObjectFactory.getInstance();
            AccountTransactionReportsExecutor accountTransactionReportsExecutor = objectFactory.getAccountTransactionReportsExecutor();
            accountTransactionReportsExecutor.execute(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
