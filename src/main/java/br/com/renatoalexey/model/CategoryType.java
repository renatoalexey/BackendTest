package br.com.renatoalexey.model;

public enum CategoryType {
    DIVERSAO ("Diversão"),
    VIAGEM ("Viagem"),
    TRANSPORTE ("Transporte"),
    HOSPEDAGEM ("Hospedagem"),
    ALIMENTACAO ("Alimentação"),
    VESTUARIO ("Vestuário"),
    HIGIENE ("Higiene"),
    OUTRAS ("Outras");

    private String categoryName;

    CategoryType(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
