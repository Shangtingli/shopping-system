package com.springboot.project.onlineShop.service;

import static org.junit.Assert.*;

import com.springboot.project.onlineShop.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProductServiceTest {

    private ProductService productService;

    @Test
    public void sayHello(){
        System.out.println("Hello");
        System.out.println(productService == null);
    }
//    @Test
//    public void saveOne() throws Exception {
//        productService.addProduct(new Product());
//    }
}