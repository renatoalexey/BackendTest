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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");

        String[] lineFields = currentLine.split("");
        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setDate(simpleDateFormat.parse(lineFields[0]));
        accountTransactionDTO.setDescription(lineFields[1]);
        accountTransactionDTO.setValue(Double.parseDouble(lineFields[2]));
        accountTransactionDTO.setCategoria(CategoryType.valueOf(Utils.removeAccentsAndSetsToUpperCase(lineFields[3])));

        return accountTransactionDTO;
    }
}
