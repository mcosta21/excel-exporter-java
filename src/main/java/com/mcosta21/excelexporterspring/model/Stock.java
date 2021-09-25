package com.mcosta21.excelexporterspring.model;

import lombok.Data;

@Data
public class Stock {

    private Long id;

    @ExportableField
    private String description;

    public Stock(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return description + " @ToString";
    }
}
