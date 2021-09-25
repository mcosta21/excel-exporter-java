package com.mcosta21.excelexporterspring.service;

import com.mcosta21.excelexporterspring.model.Category;
import com.mcosta21.excelexporterspring.model.Product;
import com.mcosta21.excelexporterspring.model.ProductResponse;
import com.mcosta21.excelexporterspring.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class DataService implements IDataService {

    @Override
    public List<Product> findAllProduct() {

        var products = new ArrayList<Product>();
        for(int i = 0; i < 30; i++) {
            var code = Long.valueOf(i+1);
            var category = Category.values()[new Random().nextInt(Category.values().length)];
            var stock = new Stock(1L, "Estoque 1");
            var product = new Product(code, "Produto " + code, Math.random(), category, stock);
            products.add(product);
        }

        return products;
    }

    @Override
    public List<ProductResponse> findAllProductResponse() {
        var products = this.findAllProduct();

        var response = new ArrayList<ProductResponse>();
        for(Product product : products){
            response.add(new ProductResponse(product));
        }

        return response;
    }

    @Override
    public List<Stock> findAllStock() {
        var stocks = new ArrayList<Stock>();
        for(int i = 0; i < 5; i++) {
            var code = Long.valueOf(i+1);
            var stock = new Stock(1L, "Estoque " + code);
            stocks.add(stock);
        }
        return stocks;
    }

}
