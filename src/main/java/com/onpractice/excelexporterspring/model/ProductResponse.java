package com.onpractice.excelexporterspring.model;

import lombok.Getter;

@Getter
public class ProductResponse {

    @ExportableField(name = "Código")
    private final Long code;

    @ExportableField(name = "Nome do produto")
    private final String description;

    @ExportableField(name = "Preço")
    private Double price = 0.0;

    @ExportableField(name = "Categoria")
    private final Category category;

    @ExportableField(name = "Estoque")
    private final String stockDescription;

    public ProductResponse(Product product) {
        this.code = product.getCode();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.stockDescription = product.getStock().getDescription();
    }
}
