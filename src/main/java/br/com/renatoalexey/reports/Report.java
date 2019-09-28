package br.com.renatoalexey.reports;

import br.com.renatoalexey.model.AccountTransactionDTO;

public interface Report {

    void buildsReportInformation(AccountTransactionDTO accountTransactionDTO);

}
