package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.config.ApplicationConfig;
import com.springboot.project.onlineShop.config.RabbitMQConfig;
import com.springboot.project.onlineShop.config.RedisConfig;
import com.springboot.project.onlineShop.config.SecurityConfig;
import com.springboot.project.onlineShop.controller.util.CustomersUtil;
import com.springboot.project.onlineShop.model.Customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.springboot.project.onlineShop.util.LogWriter;
import com.springboot.project.onlineShop.model.Product;
import com.springboot.project.onlineShop.service.CustomerService;
import com.springboot.project.onlineShop.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RedisConfig.class, ApplicationConfig.class, SecurityConfig.class, RabbitMQConfig.class})
@WebAppConfiguration
@TestPropertySource("classpath:application-test.properties")
public class CartItemHighConcurrencyControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CartItemHighConcurrencyControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private LogWriter logWriter;

    private CustomersUtil customersUtil;

    private Long productId;

    @Value("${unit-stock}")
    private int stock;

    @Value("${process-time}")
    private int process_time;

    @Value("${request-interval}")
    private int request_interval;

    @Value("${num_requests}")
    private int num_requests;


    /**
     * Best to empty the database before running the test
     */
    @Before
    public void setUp(){
        customersUtil = new CustomersUtil();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        for (Customer customer : customersUtil.getAllCustomers()) {
            customerService.addCustomer(customer);
        }
        List<Product> products = productService.getAllProducts();
        assert(products.size() == 1);
        Product product = products.get(0);
        assert(product.getUnitStock() == stock);
        productId = product.getId();
    }


    //Important: Need to give the server enough time to complete the requests in queue.
    // When the test is over, the mockMVC serer would be down.
    @Test
    public void HighConcurrencyTest()
    {
        processRequests();
        if (process_time > request_interval) {
            int time = num_requests * process_time;
            log.info("Sleeping for {} seconds for receiver part to finish", time / 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logWriter.write();
//        customerService.removeAll();
//        productService.removeAll();
    }

//    @Test
//    public void HighConcurrencyTestWhenRequestsSmallerThanUnitStock()
//    {
//        //Begin Madness
//        int numRequests = 10;
//        processRequests(numRequests);
//
//
//    }

    public void processRequests(){
        try {
            for (int i = 0; i < num_requests; i++) {
                Customer customer = customersUtil.getRandomCustomer();
                Long customerId = customer.getId();
                String path = "/madness/cart/add/"+ customerId+ "/" + productId;
                try {
                    Thread.sleep(request_interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mockMvc.perform(put(path));

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//    @After
//    public void destroy(){
//        customerService.removeAll();
//        productService.removeAll();
//    }
}