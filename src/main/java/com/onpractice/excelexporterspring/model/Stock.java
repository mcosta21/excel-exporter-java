package com.onpractice.excelexporterspring.model;

import lombok.Data;

@Data
public class Stock {

    private Long id;

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
