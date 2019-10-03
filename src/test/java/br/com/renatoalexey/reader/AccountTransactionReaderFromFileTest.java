package br.com.renatoalexey.reader;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class AccountTransactionReaderFromFileTest {

    private AccountTransactionReaderFromFile accountTransactionReaderFromFile;

    private List<AccountTransactionDTO> listForTests;

    @Before
    public void setup() throws ParseException {
        listForTests = createListForTests();
    }


    @Test
    public void testFileReader() throws IOException, ParseException {
        accountTransactionReaderFromFile = new AccountTransactionReaderFromFile();

        List<AccountTransactionDTO> accountTransactionDTOList = accountTransactionReaderFromFile.transformsFileIntoDTO("src/test/resource/account_transactions_minor.log");

        for (int i = 0;  i < accountTransactionDTOList.size(); i ++) {
            AccountTransactionDTO accountTransactionDTOExpected = listForTests.get(i);
            AccountTransactionDTO accountTransactionDTOActual = accountTransactionDTOList.get(i);
            Assert.assertEquals(accountTransactionDTOExpected, accountTransactionDTOActual);
        }

    }

    private List<AccountTransactionDTO> createListForTests() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM");

        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setCategoria(CategoryType.DIVERSAO);
        accountTransactionDTO.setValue(-159.53);
        accountTransactionDTO.setDescription("INGRESSO.COM");
        accountTransactionDTO.setDate(simpleDateFormat.parse("21-Mar"));

        AccountTransactionDTO accountTransactionDTO2 = new AccountTransactionDTO();
        accountTransactionDTO2.setCategoria(CategoryType.VIAGEM);
        accountTransactionDTO2.setValue(-430.49);
        accountTransactionDTO2.setDescription("TAM SITE");
        accountTransactionDTO2.setDate(simpleDateFormat.parse("17-Fev"));

        AccountTransactionDTO accountTransactionDTO3 = new AccountTransactionDTO();
        accountTransactionDTO3.setCategoria(CategoryType.OUTRAS);
        accountTransactionDTO3.setValue(-213.26);
        accountTransactionDTO3.setDescription("PONTO FRIOCOM");
        accountTransactionDTO3.setDate(simpleDateFormat.parse("27-Mar"));

        AccountTransactionDTO accountTransactionDTO4 = new AccountTransactionDTO();
        accountTransactionDTO4.setCategoria(CategoryType.ALIMENTACAO);
        accountTransactionDTO4.setValue(-13);
        accountTransactionDTO4.setDescription("Hirota");
        accountTransactionDTO4.setDate(simpleDateFormat.parse("29-Mai"));

        AccountTransactionDTO accountTransactionDTO5 = new AccountTransactionDTO();
        accountTransactionDTO5.setCategoria(CategoryType.ALIMENTACAO);
        accountTransactionDTO5.setValue(-62.43);
        accountTransactionDTO5.setDescription("Evino");
        accountTransactionDTO5.setDate(simpleDateFormat.parse("24-Mai"));

        AccountTransactionDTO accountTransactionDTO7 = new AccountTransactionDTO();
        accountTransactionDTO7.setCategoria(CategoryType.VESTUARIO);
        accountTransactionDTO7.setValue(-59.95);
        accountTransactionDTO7.setDescription("LOJAS RENNER FL");
        accountTransactionDTO7.setDate(simpleDateFormat.parse("26-Abr"));

        AccountTransactionDTO accountTransactionDTO8 = new AccountTransactionDTO();
        accountTransactionDTO8.setCategoria(CategoryType.HOSPEDAGEM);
        accountTransactionDTO8.setValue(-1430.44);
        accountTransactionDTO8.setDescription("EBANX AIRBNB");
        accountTransactionDTO8.setDate(simpleDateFormat.parse("27-Mai"));

        AccountTransactionDTO accountTransactionDTO9 = new AccountTransactionDTO();
        accountTransactionDTO9.setCategoria(CategoryType.HIGIENE);
        accountTransactionDTO9.setValue(-14.09);
        accountTransactionDTO9.setDescription("Droga Raia");
        accountTransactionDTO9.setDate(simpleDateFormat.parse("25-Mai"));

        AccountTransactionDTO accountTransactionDTO10 = new AccountTransactionDTO();
        accountTransactionDTO10.setCategoria(CategoryType.ALIMENTACAO);
        accountTransactionDTO10.setValue(-55.9);
        accountTransactionDTO10.setDescription("NESPRESSO");
        accountTransactionDTO10.setDate(simpleDateFormat.parse("21-Mar"));

        AccountTransactionDTO accountTransactionDTO11 = new AccountTransactionDTO();
        accountTransactionDTO11.setCategoria(CategoryType.TRANSPORTE);
        accountTransactionDTO11.setValue(-10.0);
        accountTransactionDTO11.setDescription("RECARGAPAY BILH UNICO");
        accountTransactionDTO11.setDate(simpleDateFormat.parse("02-Jun"));

        AccountTransactionDTO accountTransactionDTO12 = new AccountTransactionDTO();
        accountTransactionDTO12.setCategoria(CategoryType.TRANSPORTE);
        accountTransactionDTO12.setValue(-7.03);
        accountTransactionDTO12.setDescription("Uber Do Brasil Tecnolog");
        accountTransactionDTO12.setDate(simpleDateFormat.parse("05-Jun"));

        return Arrays.asList(accountTransactionDTO, accountTransactionDTO2, accountTransactionDTO3, accountTransactionDTO4, accountTransactionDTO5,
                accountTransactionDTO7, accountTransactionDTO8, accountTransactionDTO9, accountTransactionDTO10, accountTransactionDTO11,
                accountTransactionDTO12);
    }
}
