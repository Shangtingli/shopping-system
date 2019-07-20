package com.springboot.project.onlineShop.service;

import static org.junit.Assert.*;

import com.springboot.project.onlineShop.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void sayHello(){
        System.out.println("Hello");
        System.out.println(productService == null);
    }
    @Test
    public void saveOne() throws Exception {
        productService.deleteAll();
        Product product = new Product();
        product.setProductName("Test1");
        productService.addProduct(product);
        Product product2 = new Product();
        product2.setProductName("Test2");
        productService.addProduct(product2);
        List<Product> products = productService.getAllProducts();
        assert(products.size()== 2);
        productService.deleteAll();
    }
}