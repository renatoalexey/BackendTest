package br.com.renatoalexey.config;

import br.com.renatoalexey.reports.CategoryReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CategoryReport categoryReport() {
        return new CategoryReport();
    }
}
