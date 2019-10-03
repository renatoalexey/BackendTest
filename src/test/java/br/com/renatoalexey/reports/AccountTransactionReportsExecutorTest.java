package br.com.renatoalexey.reports;

import br.com.renatoalexey.factory.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class AccountTransactionReportsExecutorTest {

    private AccountTransactionReportsExecutor accountTransactionReportsExecutor;

    @Before
    public void setup(){
        accountTransactionReportsExecutor = new AccountTransactionReportsExecutor(ObjectFactory.getInstance());
    }

    /**
     * Integration test for the program
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void systemTest() throws IOException, ParseException {
        accountTransactionReportsExecutor.execute("src/test/resource/account_transactions.log");
    }
}
