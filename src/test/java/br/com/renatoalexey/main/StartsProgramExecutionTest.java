package br.com.renatoalexey.main;

import br.com.renatoalexey.factory.ObjectFactory;
import br.com.renatoalexey.main.StartsProgramExecution;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class StartsProgramExecutionTest {

    private StartsProgramExecution startsProgramExecution;

    @Before
    public void setup(){
        ObjectFactory objectFactory = ObjectFactory.init();
        startsProgramExecution = objectFactory.getStartsProgramExecution();
    }

    /**
     * Integration test for the program
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void systemTest() throws IOException, ParseException {
        startsProgramExecution.run("src/test/resource/account_transactions.log");
    }
}
