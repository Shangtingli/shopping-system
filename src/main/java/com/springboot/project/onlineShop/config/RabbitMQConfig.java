package com.springboot.project.onlineShop.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    @Value("${customer_mq_queue}")
    private String customerQueueName;

    @Value("${rabbit_mq_exchange}")
    private String exchangeName;

    @Value("${customer_mq_routing_key}")
    private String customerRoutingKey;

//    @Value("${product_mq_routing_key}")
//    private String productRoutingKey;
//
//    @Value("${product_mq_queue}")
//    private String productQueueName;

    @Bean
    public Queue queue1(){
        return new Queue(customerQueueName, false);
    }

//    @Bean
//    public Queue queue2(){
//        return new Queue(productQueueName, false);
//    }
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding1(Queue queue1, DirectExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(customerRoutingKey);
    }

//    @Bean
//    public Binding binding2(Queue queue2, DirectExchange exchange){
//        return BindingBuilder.bind(queue2).to(exchange).with(productRoutingKey);
//    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
