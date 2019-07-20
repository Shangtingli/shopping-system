package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long productId);

    void deleteProduct(Long productId);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteAll();
}
