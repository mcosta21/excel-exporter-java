package com.onpractice.excelexporterspring.service;


import com.onpractice.excelexporterspring.model.Product;
import com.onpractice.excelexporterspring.model.ProductResponse;
import com.onpractice.excelexporterspring.model.Stock;

import java.util.List;

public interface IDataService {

    List<Product> findAllProduct();
    List<ProductResponse> findAllProductResponse();
    List<Stock> findAllStock();

}
