package com.springboot.project.onlineShop.amqp;

import com.springboot.project.onlineShop.util.LogWriter;
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

    public void send(String[] ids) {
        amqpTemplate.convertAndSend(exchange, routingkey, ids);
        logWriter.insert("Message for Customer " + ids[0] + " for " + ids[1] + " sent");
//        return;
//        log.info("Message Sent For {}:" + customerProductDto.getCustomer());
    }
}
