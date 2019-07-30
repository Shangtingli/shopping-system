package com.springboot.project.onlineShop.amqp;
import com.springboot.project.onlineShop.model.Authorities;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.CustomerBuilder.CustomerBuilderFromMessage;
import com.springboot.project.onlineShop.service.AuthoritiesService;
import com.springboot.project.onlineShop.service.CustomerService;
import com.springboot.project.onlineShop.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class CustomerMQReceiver {

    private static final Logger log = LoggerFactory.getLogger(CustomerMQReceiver.class);
    private static final int SLEEP_TIME = 60000;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "#{queue1.name}")
    public void receiveMessage(final Message message) {
        log.info("Received Message {}", message.toString());

        try{
            Thread.sleep(20000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        CustomerBuilderFromMessage builder = new CustomerBuilderFromMessage();
        builder.setMessage(message);
        Customer customer = builder.build();
        customerService.addCustomer(customer);
        authoritiesService.addAuthorities(new Authorities(null,customer.getUser().getEmailId(),"ROLE_USER"));
        log.info("Finished Adding Customers to Database");
    }

}