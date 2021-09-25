package com.mcosta21.excelexporterspring.controller;

import com.mcosta21.excelexporterspring.model.Category;
import com.mcosta21.excelexporterspring.model.Product;
import com.mcosta21.excelexporterspring.model.Stock;
import com.mcosta21.excelexporterspring.service.ExcelExporterService;
import com.mcosta21.excelexporterspring.service.IDataService;
import com.mcosta21.excelexporterspring.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private IDataService dataService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> products(){
        return ResponseEntity.ok(dataService.findAllProduct());
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> stocks(){
        return ResponseEntity.ok(dataService.findAllStock());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories(){
        return ResponseEntity.ok(Arrays.asList(Category.values()));
    }

    @GetMapping("/products/response")
    public ResponseEntity<List<ProductResponse>> productsResponse(){
        return ResponseEntity.ok(dataService.findAllProductResponse());
    }

    @GetMapping("/export/products")
    public void exportProductsToExcel(HttpServletResponse response) throws IOException, IllegalAccessException {

        var products = dataService.findAllProduct();

        ExcelExporterService excelExporterService = new ExcelExporterService();
        excelExporterService.export(response, "Produtos", products);
    }

    @GetMapping("/export/stocks")
    public void exportStocks(HttpServletResponse response) throws IOException, IllegalAccessException {
        var stocks = dataService.findAllStock();
        ExcelExporterService excelExporterService = new ExcelExporterService();
        excelExporterService.export(response, "Estoques", stocks);
    }

    @GetMapping("/export/products/response")
    public void exportProductsResponseToExcel(HttpServletResponse response) throws IOException, IllegalAccessException {
        var products = dataService.findAllProductResponse();
        ExcelExporterService excelExporterService = new ExcelExporterService();
        excelExporterService.export(response, "Produtos (Modelo Response)", products);
    }
}
