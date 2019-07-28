package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.amqp.ProductMQSender;
import com.springboot.project.onlineShop.model.*;
import com.springboot.project.onlineShop.repository.ProductRepository;
import com.springboot.project.onlineShop.service.CustomerService;
import com.springboot.project.onlineShop.service.ProductService;
import com.springboot.project.onlineShop.service.RedisService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    //TODO: AfterPropertiesSet runs before @Before Annotated Class. Causes Inconsistency
    @Override
    public void afterPropertiesSet() throws Exception {
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
    public String rob(@PathVariable(value = "productId") Long productId,
                      @PathVariable(value="customerId") Long customerId) {
        long stock = redisService.decr(Long.toString(productId));
        if (stock < 0){
            logWriter.insert("Request Not Completed");
            return "error/soldout";
        }
        return productMQSender.send(new String[]{Long.toString(customerId),Long.toString(productId)});
    }
}
