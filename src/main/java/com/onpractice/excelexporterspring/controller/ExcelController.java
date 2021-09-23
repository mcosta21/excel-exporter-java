package com.onpractice.excelexporterspring.controller;

import com.onpractice.excelexporterspring.model.Product;
import com.onpractice.excelexporterspring.model.ProductResponse;
import com.onpractice.excelexporterspring.service.ExcelExporterService;
import com.onpractice.excelexporterspring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> products(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/products/response")
    public ResponseEntity<List<ProductResponse>> productsResponse(){
        return ResponseEntity.ok(productService.findAllResponse());
    }

    @GetMapping("/export/products")
    public void exportProductsToExcel(HttpServletResponse response) throws IOException, IllegalAccessException {
        var products = productService.findAll();
        ExcelExporterService excelExporterService = new ExcelExporterService();
        excelExporterService.export(response, "Produtos", products);
    }

    @GetMapping("/export/products/response")
    public void exportProductsResponseToExcel(HttpServletResponse response) throws IOException, IllegalAccessException {
        var products = productService.findAllResponse();
        ExcelExporterService excelExporterService = new ExcelExporterService();
        excelExporterService.export(response, "Produtos (Modelo Response)", products);
    }
}
