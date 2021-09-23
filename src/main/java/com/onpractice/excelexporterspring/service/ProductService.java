package com.onpractice.excelexporterspring.service;

import com.onpractice.excelexporterspring.model.Category;
import com.onpractice.excelexporterspring.model.Product;
import com.onpractice.excelexporterspring.model.ProductResponse;
import com.onpractice.excelexporterspring.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class ProductService implements IProductService {

    @Override
    public List<Product> findAll() {

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
    public List<ProductResponse> findAllResponse() {
        var products = this.findAll();

        var response = new ArrayList<ProductResponse>();
        for(Product product : products){
            response.add(new ProductResponse(product));
        }

        return response;
    }

}
