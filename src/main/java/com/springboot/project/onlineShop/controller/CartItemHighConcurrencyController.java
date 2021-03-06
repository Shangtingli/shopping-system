package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.amqp.ProductMQSender;
import com.springboot.project.onlineShop.model.*;
import com.springboot.project.onlineShop.service.ProductService;
import com.springboot.project.onlineShop.service.RedisService;
import com.springboot.project.onlineShop.util.LogWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/madness")
public class CartItemHighConcurrencyController  implements InitializingBean {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMQSender productMQSender;


    @Autowired
    private LogWriter logWriter;


    @Value("${unit-stock}")
    private int stock;


    @Value("${request-interval}")
    private int request_interal;


    @Override
    public void afterPropertiesSet() throws Exception {
        Product product_test = new Product();
        product_test.setProductName("TestProduct");
        product_test.setUnitStock(stock);
        product_test.setProductCategory("Android");
        product_test.setProductDescription("Testing Product");
        product_test.setProductManufacturer("Samsung");
        product_test.setProductPrice(1000);
        productService.addProduct(product_test);
        List<Product> productList = productService.getAllProducts();
        if (productList == null) {
            return;
        }
        for (Product product: productList) {
            redisService.set(Long.toString(product.getId()), product.getUnitStock());
        }

        System.out.println();
    }

    @RequestMapping(value = "/cart/add/{customerId}/{productId}", method = RequestMethod.PUT)
    @ResponseBody
    public String rob(@PathVariable(value = "productId") Long productId,
                      @PathVariable(value="customerId") Long customerId) {
        productMQSender.send(new String[]{Long.toString(customerId),Long.toString(productId)});

        return "Still Processing Your Request...";
    }
}
