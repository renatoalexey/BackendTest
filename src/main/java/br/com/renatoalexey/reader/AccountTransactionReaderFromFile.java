package br.com.renatoalexey.reader;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.model.CategoryType;
import br.com.renatoalexey.reports.AccountTransactionReportsExecutor;
import br.com.renatoalexey.utils.Utils;

import java.io.*;
import java.text.ParseException;
import java.util.Locale;

public class AccountTransactionReaderFromFile {

    private AccountTransactionReportsExecutor accountTransactionReportsExecutor;

    public AccountTransactionReaderFromFile(AccountTransactionReportsExecutor accountTransactionReportsExecutor) {
        this.accountTransactionReportsExecutor = accountTransactionReportsExecutor;
    }

    public void generatesReportsFromFile(String filePath) throws IOException,
            ParseException {

        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file),"UTF-8"));
        String currentLine;

        while ((currentLine = bufferedReader.readLine()) != null) {
            AccountTransactionDTO accountTransactionDTO = transformsFileLineIntoAccountTransactionDTO(currentLine);
            accountTransactionReportsExecutor.buildsAllReportsData(accountTransactionDTO);
        }
    }

    private AccountTransactionDTO transformsFileLineIntoAccountTransactionDTO(String currentLine) throws ParseException {
        String[] lineFields = currentLine.split("[|]");
        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
        accountTransactionDTO.setDate(Utils.getDateFromString(lineFields[2], "dd-MMM", Locale.US));
        accountTransactionDTO.setDescription(Utils.removeWhiteSpaceFromBeginningAndEnd(lineFields[3]));
        accountTransactionDTO.setValue(Utils.getValueFromString(lineFields[4]));
        if(lineFields.length == 6)
            accountTransactionDTO.setCategoryType(Utils.getCategoryTypeFromString(lineFields[5]));
        else accountTransactionDTO.setCategoryType(CategoryType.OUTRAS);

        return accountTransactionDTO;
    }
}
