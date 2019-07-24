package com.springboot.project.onlineShop.amqp;

import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ProductMQSender {

    private static final Logger log = LoggerFactory.getLogger(ProductMQSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbit_mq_exchange}")
    private String exchange;

    @Value("${product_mq_routing_key}")
    private String routingkey;

    public void send(Product product) {
        amqpTemplate.convertAndSend(exchange, routingkey, product);
        log.info("Message Sent For {}:" + product.getProductName());
    }
}
