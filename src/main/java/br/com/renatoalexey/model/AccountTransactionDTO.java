package br.com.renatoalexey.model;

import java.util.Date;

public class AccountTransactionDTO {
    private Date date;
    private String description;
    private double value;
    private CategoryType categoria;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public CategoryType getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryType categoria) {
        this.categoria = categoria;
    }
}
