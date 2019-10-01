package br.com.renatoalexey.reader;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import br.com.renatoalexey.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class AccountTransactionReaderFromFile {

    public List<AccountTransactionDTO> transformsFileIntoDTO(BufferedReader bufferedReader) throws IOException,
            ParseException {
        List<AccountTransactionDTO> accountTransactionDTOList = new ArrayList<AccountTransactionDTO>();

        String currentLine = "";

        while ((currentLine = bufferedReader.readLine()) != null) {
            accountTransactionDTOList.add(transformsFileLineIntoAccountTransactionDTO(currentLine));
        }

        return accountTransactionDTOList;
    }

    private AccountTransactionDTO transformsFileLineIntoAccountTransactionDTO(String currentLine) throws ParseException {

        String[] lineFields = currentLine.split("[|]");
        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setDate(Utils.getDateFromString(lineFields[2], "dd-MMM", Locale.US));
        accountTransactionDTO.setDescription(lineFields[3]);
        accountTransactionDTO.setValue(Utils.getValueFromString(lineFields[4]));
        if(lineFields.length == 6)
            accountTransactionDTO.setCategoria(Utils.getCategoryTypeFromString(lineFields[5]));

        return accountTransactionDTO;
    }
}
