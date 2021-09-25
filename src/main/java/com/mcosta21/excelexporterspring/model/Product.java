package com.mcosta21.excelexporterspring.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Product {

    @EqualsAndHashCode.Include
    @ExportableField(name = "Código")
    private Long code;

    @ExportableField(name = "Nome do produto")
    private String description;

    @ExportableField(name = "Preço")
    private Double price = 0.0;

    @ExportableField(name = "Categoria")
    private Category category;

    @ExportableField()
    private Stock stock;

    public Product(Long code, String description, Double price, Category category, Stock stock) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }
}
