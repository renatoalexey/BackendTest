package br.com.renatoalexey.main;

import br.com.renatoalexey.factory.ObjectFactory;
import br.com.renatoalexey.reports.AccountTransactionReportsExecutor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
        try {
            ObjectFactory objectFactory = ObjectFactory.init();
            StartsProgramExecution startsProgramExecution = objectFactory.getStartsProgramExecution();
            startsProgramExecution.run(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
