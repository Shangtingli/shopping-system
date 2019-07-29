package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.config.ApplicationConfig;
import com.springboot.project.onlineShop.config.RabbitMQConfig;
import com.springboot.project.onlineShop.config.RedisConfig;
import com.springboot.project.onlineShop.config.SecurityConfig;
import com.springboot.project.onlineShop.controller.util.CustomersUtil;
import com.springboot.project.onlineShop.model.Customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.springboot.project.onlineShop.model.LogWriter;
import com.springboot.project.onlineShop.model.Product;
import com.springboot.project.onlineShop.service.CustomerService;
import com.springboot.project.onlineShop.service.ProductService;
import org.junit.After;
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

    @Value("${test-unit-stock}")
    private int stock;

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


    @Test
    public void HighConcurrencyTestWhenRequestsGreaterThanUnitStock()
    {
        //Begin Madness
        int numRequests = 30;
        processRequests(numRequests);
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

    public void processRequests(int numRequests){
        try {
            for (int i = 0; i < numRequests; i++) {
                Customer customer = customersUtil.getRandomCustomer();
                Long customerId = customer.getId();
                String path = "/madness/cart/add/"+ customerId+ "/" + productId;
//                Thread.sleep(113);
                mockMvc.perform(put(path));

            }
            logWriter.write();
            assert(logWriter.getSuccess() + logWriter.getFailure() == numRequests);
        }
        catch(Exception e){
            e.printStackTrace();
        }
//        finally{
//            customerService.removeAll();
//            productService.removeAll();
//        }
    }

    @After
    public void destroy(){
        customerService.removeAll();
        productService.removeAll();
    }
}