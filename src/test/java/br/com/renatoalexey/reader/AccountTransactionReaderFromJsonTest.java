package br.com.renatoalexey.reader;

import br.com.renatoalexey.config.AppConfig;
import br.com.renatoalexey.connect.ConnectsToJsonAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.URL;
import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AccountTransactionReaderFromJsonTest {

    private String json;

    private AccountTransactionReaderFromJson accountTransactionReaderFromJson;

    @Mock
    private ConnectsToJsonAPI connectsToJsonAPI;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        try {
            FileReader fileReader = new FileReader("src/test/resource/db.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String currentLine = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(currentLine);
            }

            json = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsonReader() {
        try {
            accountTransactionReaderFromJson = new AccountTransactionReaderFromJson(connectsToJsonAPI);
            when(connectsToJsonAPI.connectsToAPI("test")).thenReturn(null);
            when(connectsToJsonAPI.readJsonFromUrl(null)).thenReturn(json);
            accountTransactionReaderFromJson.transformsJsonIntoDTO("test");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
