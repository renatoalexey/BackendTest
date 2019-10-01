package br.com.renatoalexey.reader;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class AccountTransactionReaderFromFileTest {

    private AccountTransactionReaderFromFile accountTransactionReaderFromFile;

    @Test
    public void testFileReader() {
        accountTransactionReaderFromFile = new AccountTransactionReaderFromFile();
        try {
            accountTransactionReaderFromFile.transformsFileIntoDTO("src/test/resource/account_transactions.log");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
