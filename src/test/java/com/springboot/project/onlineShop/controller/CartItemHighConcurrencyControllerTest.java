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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

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

    private int initialStock;

    private CustomersUtil customersUtil;
    @Before
    public void setUp(){
        customersUtil = new CustomersUtil();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        for (Customer customer : customersUtil.getAllCustomers()) {
            customerService.addCustomer(customer);
        }

        product = new Product();
        initialStock = CustomersUtil.UNIT_STOCK;
        product.setUnitStock(CustomersUtil.UNIT_STOCK);
        product.setProductManufacturer(CustomersUtil.PRODUCT_PRODUCER);
        product.setProductCategory(CustomersUtil.CATEGORY);
        product.setProductName(CustomersUtil.PRODUCT_NAME);
        productService.addProduct(product);
        List<Product> productsFromDB = productService.getAllProducts();
        assert(productsFromDB.size() > 0);
        productId = productsFromDB.get(0).getId();
    }


    @Test
    public void HighConcurrencyTestWhenRequestsGreaterThanUnitStock()
    {
        //Begin Madness
        int numRequests = 20;

        try {
            for (int i = 0; i < numRequests; i++) {
                Customer customer = customersUtil.getRandomCustomer();
                Long customerId = customer.getId();
                String path = "/madness/cart/add/"+ customerId+ "/" + productId;
                logWriter.insert("Sending Request for customer "  + customer.getId() +  ", RequestNumber: "+ i);
                mockMvc.perform(put(path));
                Thread.sleep(113);
            }
//            assert(logWriter.getSuccess() + logWriter.getFailure() == numRequests);
//            assert(logWriter.getSuccess() == initialStock);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            customerService.removeAll();
            productService.removeAll();
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