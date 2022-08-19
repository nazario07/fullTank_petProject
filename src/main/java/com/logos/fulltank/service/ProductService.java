package com.logos.fulltank.service;

import com.logos.fulltank.entity.Product;
import com.logos.fulltank.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAll();
    Product getById(int id) throws ProductNotFoundException;
    void deleteProduct(int id) throws ProductNotFoundException;
}
