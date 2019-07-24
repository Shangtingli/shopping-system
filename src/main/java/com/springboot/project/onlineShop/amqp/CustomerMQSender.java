package com.springboot.project.onlineShop.amqp;


import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomerMQSender {
    private static final Logger log = LoggerFactory.getLogger(CustomerMQSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${customer_mq_exchange}")
    private String exchange;

    @Value("${customer_mq_routing_key}")
    private String routingkey;

    public void send(Customer customer) {
        amqpTemplate.convertAndSend(exchange, routingkey, customer);
        log.info("Message Sent For {}:" ,customer.getFirstName() + " " + customer.getLastName());
    }
}
