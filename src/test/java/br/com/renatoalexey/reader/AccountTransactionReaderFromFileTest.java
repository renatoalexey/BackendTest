package br.com.renatoalexey.reader;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class AccountTransactionReaderFromFileTest {
    private BufferedReader bufferedReader;

    private AccountTransactionReaderFromFile accountTransactionReaderFromFile;

    @Before
    public void setup() {
        try {
            FileReader fileReader = new FileReader("src/test/resource/account_transactions.log");
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileReader() {
        accountTransactionReaderFromFile = new AccountTransactionReaderFromFile();
        try {
            accountTransactionReaderFromFile.transformsFileIntoDTO(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
