package com.springboot.project.onlineShop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.project.onlineShop.config.ApplicationConfig;
import com.springboot.project.onlineShop.config.RabbitMQConfig;
import com.springboot.project.onlineShop.config.RedisConfig;
import com.springboot.project.onlineShop.config.SecurityConfig;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.CustomerBuilder.CustomerBasicBuilder;
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
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.springboot.project.onlineShop.controller.ControllerUtil.getCustomer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    private WebApplicationContext context;

    @Autowired
    private ProductService productService;

    @Autowired
    private LogWriter logWriter;

    private Customer customer;

    private Product product;

    private Long productId;

    private Long customerId;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        customer = ControllerUtil.getCustomer();
        customerService.addCustomer(customer);
        Customer customerFromDB = customerService.getCustomerByUserName(ControllerUtil.EMAIL);
        assert(customerFromDB!= null);

        product = new Product();
        product.setUnitStock(ControllerUtil.UNIT_STOCK);
        product.setProductManufacturer(ControllerUtil.PRODUCT_PRODUCER);
        product.setProductCategory(ControllerUtil.CATEGORY);
        product.setProductName(ControllerUtil.PRODUCT_NAME);
        productService.addProduct(product);
        List<Product> productsFromDB = productService.getAllProducts();
        assert(productsFromDB.size() > 0);
        productId = productsFromDB.get(0).getId();
        customerId = customer.getId();
    }


    @Test
    public void HighConcurrencyTest()
    {
        //Begin Madness
        int numRequests = 1000;
        String path = "/madness/cart/add/"+ customerId+ "/" + productId;
        try {
            for (int i = 0; i < numRequests; i++) {
                logWriter.insert("Sending Request for user, RequestNumber: "+ i);
                mockMvc.perform(put(path)).andReturn();
                Thread.sleep(100);


//                log.info("Sending Request for user, RequestNumber: {} ", i);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            customerService.removeCustomerByUserName(ControllerUtil.EMAIL);
            productService.deleteProduct(productId);
            logWriter.write();
        }

    }
//
//    @After
//    public void DestroyData(){
//        customerService.removeCustomerByUserName(ControllerUtil.EMAIL);
//        productService.deleteProduct(productId);
//    }
}