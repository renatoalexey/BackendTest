package br.com.renatoalexey.model;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDTO that = (AccountTransactionDTO) o;
        return Double.compare(that.value, value) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(description, that.description) &&
                categoria == that.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, value, categoria);
    }
}
