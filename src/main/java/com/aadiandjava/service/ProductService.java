package com.aadiandjava.service;

import java.util.List;


import com.aadiandjava.entity.Product;

public interface ProductService {

    void saveProduct(Product product);

    List<Product> getAllProducts();
    public abstract Product getById(int id);

    public void deleteById(int id);
}