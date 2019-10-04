package br.com.renatoalexey.reader;

import br.com.renatoalexey.connect.ConnectsToJsonAPI;
import br.com.renatoalexey.factory.ObjectFactory;
import br.com.renatoalexey.utils.UtilsForTests;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;


import static org.mockito.Mockito.when;

public class AccountTransactionReaderFromJsonTest {

    private String json;

    private AccountTransactionReaderFromJson accountTransactionReaderFromJson;

    @Mock
    private ConnectsToJsonAPI connectsToJsonAPI;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        try {
           json = UtilsForTests.readsJsonFromFile("src/test/resource/db.txt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that basically verifies if reader doesn't throw exceptions
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testJsonReader() throws IOException, ParseException {
            accountTransactionReaderFromJson = new AccountTransactionReaderFromJson(connectsToJsonAPI, ObjectFactory.init().getAccountTransactionReportsExecutor());
            when(connectsToJsonAPI.connectsToAPI("test")).thenReturn(null);
            when(connectsToJsonAPI.readJsonFromUrl(null)).thenReturn(json);
            accountTransactionReaderFromJson.generatesReportsFromJSON("test");
    }
}
