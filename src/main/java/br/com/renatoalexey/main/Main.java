package br.com.renatoalexey.main;

import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.reader.AccountTransactionReaderFromFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader(args[0]);

            List<AccountTransactionDTO> accountTransactionDTOListFromFile =
                    new AccountTransactionReaderFromFile().transformsFileIntoDTO(new BufferedReader(fileReader));
        } catch (FileNotFoundException e) {

        } catch (ParseException e) {

        } catch (IOException e) {

        }

    }
}
