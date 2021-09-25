package com.mcosta21.excelexporterspring.service;


import com.mcosta21.excelexporterspring.model.Product;
import com.mcosta21.excelexporterspring.model.Stock;
import com.mcosta21.excelexporterspring.model.ProductResponse;

import java.util.List;

public interface IDataService {

    List<Product> findAllProduct();
    List<ProductResponse> findAllProductResponse();
    List<Stock> findAllStock();

}
