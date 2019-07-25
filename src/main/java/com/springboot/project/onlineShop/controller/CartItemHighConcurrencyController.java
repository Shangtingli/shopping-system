package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.amqp.ProductMQSender;
import com.springboot.project.onlineShop.model.*;
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

    @Override
    public void afterPropertiesSet() throws Exception {

        List<Product> productList = productService.getAllProducts();
        if (productList == null) {
            return;
        }
        for (Product product: productList) {
            redisService.set(Long.toString(product.getId()), product.getUnitStock());
        }
    }

    @RequestMapping(value = "/cart/add/{customerId}/{productId}", method = RequestMethod.PUT)
    public String rob(@PathVariable(value = "productId") Long productId,
                      @PathVariable(value="customerId") Long customerId) {
        return productMQSender.send(new String[]{Long.toString(customerId),Long.toString(productId)});
    }
}
