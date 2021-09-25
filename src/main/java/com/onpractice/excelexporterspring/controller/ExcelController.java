package com.onpractice.excelexporterspring.controller;

import com.onpractice.excelexporterspring.model.Category;
import com.onpractice.excelexporterspring.model.Product;
import com.onpractice.excelexporterspring.model.ProductResponse;
import com.onpractice.excelexporterspring.model.Stock;
import com.onpractice.excelexporterspring.service.ExcelExporterService;
import com.onpractice.excelexporterspring.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/export/categories")
    public void exportCategories(HttpServletResponse response) throws IOException, IllegalAccessException {
        var categories = Category.values();
        ExcelExporterService excelExporterService = new ExcelExporterService();
        excelExporterService.export(response, "Categorias", categories);
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
