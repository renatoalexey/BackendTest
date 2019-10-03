package br.com.renatoalexey.reader;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import br.com.renatoalexey.utils.Utils;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccountTransactionReaderFromFile {

    public List<AccountTransactionDTO> transformsFileIntoDTO(String filePath) throws IOException,
            ParseException {

        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file),"UTF-8"));
        List<AccountTransactionDTO> accountTransactionDTOList = new ArrayList<>();

        String currentLine;

        while ((currentLine = bufferedReader.readLine()) != null) {
            accountTransactionDTOList.add(transformsFileLineIntoAccountTransactionDTO(currentLine));
        }

        return accountTransactionDTOList;
    }

    private AccountTransactionDTO transformsFileLineIntoAccountTransactionDTO(String currentLine) throws ParseException {
        String[] lineFields = currentLine.split("[|]");
        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setDate(Utils.getDateFromString(lineFields[2], "dd-MMM", Locale.US));
        accountTransactionDTO.setDescription(Utils.removeWhiteSpaceFromBeginningAndEnd(lineFields[3]));
        accountTransactionDTO.setValue(Utils.getValueFromString(lineFields[4]));
        if(lineFields.length == 6)
            accountTransactionDTO.setCategoria(Utils.getCategoryTypeFromString(lineFields[5]));
        else accountTransactionDTO.setCategoria(CategoryType.OUTRAS);

        return accountTransactionDTO;
    }
}
