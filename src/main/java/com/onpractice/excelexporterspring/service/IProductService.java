package com.onpractice.excelexporterspring.service;


import com.onpractice.excelexporterspring.model.Product;
import com.onpractice.excelexporterspring.model.ProductResponse;

import java.util.List;

public interface IProductService {

    List<Product> findAll();
    List<ProductResponse> findAllResponse();

}
