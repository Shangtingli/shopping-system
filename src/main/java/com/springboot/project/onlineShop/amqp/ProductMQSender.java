package com.springboot.project.onlineShop.amqp;

import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.LogWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductMQSender {

    private static final Logger log = LoggerFactory.getLogger(ProductMQSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private LogWriter logWriter;

    @Value("${rabbit_mq_exchange}")
    private String exchange;

    @Value("${product_mq_routing_key}")
    private String routingkey;

    public String send(String[] ids) {
        Object res = amqpTemplate.convertSendAndReceive(exchange, routingkey, ids);
        return (String)res;
//        log.info("Message Sent For {}:" + customerProductDto.getCustomer());
    }
}
