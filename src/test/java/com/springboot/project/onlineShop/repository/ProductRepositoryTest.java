package com.springboot.project.onlineShop.repository;

import com.springboot.project.onlineShop.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldUpdate(){
        Product product = new Product();
        product.setUnitStock(100);
        product.setProductName("Test Product");
        product.setProductCategory("Test");
        product.setProductDescription("Just Test");
        product.setProductManufacturer("Jason");

        productRepository.save(product);
        product.setUnitStock(1000);
        productRepository.save(product);

        List<Product> listOfProduct = (List<Product>) productRepository.findAll();
        assert(listOfProduct.size() == 1);
        Product productFromDB = listOfProduct.get(0);
        assert(productFromDB.getUnitStock() == 1000);
    }
}