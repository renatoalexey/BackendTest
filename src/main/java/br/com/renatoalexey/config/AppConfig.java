package br.com.renatoalexey.config;

import br.com.renatoalexey.connect.ConnectsToJsonAPI;
import br.com.renatoalexey.reader.AccountTransactionReaderFromJson;
import br.com.renatoalexey.reports.CategoryReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CategoryReport categoryReport() {
        return new CategoryReport();
    }

    @Bean
    public AccountTransactionReaderFromJson accountTransactionReaderFromJson() {
        return new AccountTransactionReaderFromJson(connectsToJsonAPI());
    }

    @Bean
    public ConnectsToJsonAPI connectsToJsonAPI() {
        return new ConnectsToJsonAPI();
    }
}
