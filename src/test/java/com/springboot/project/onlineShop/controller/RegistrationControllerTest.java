package com.springboot.project.onlineShop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.onlineShop.config.ApplicationConfig;
import com.springboot.project.onlineShop.config.RabbitMQConfig;
import com.springboot.project.onlineShop.config.RedisConfig;
import com.springboot.project.onlineShop.config.SecurityConfig;
import com.springboot.project.onlineShop.controller.util.CustomersUtil;
import com.springboot.project.onlineShop.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RedisConfig.class, ApplicationConfig.class, SecurityConfig.class, RabbitMQConfig.class})
@WebAppConfiguration
@TestPropertySource("classpath:application-test.properties")
public class RegistrationControllerTest {

    @Autowired
    WebApplicationContext context;

    private CustomersUtil customersUtil;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        customersUtil = new CustomersUtil();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    private MockHttpServletRequestBuilder buildGetRequest(String path) {
        return get(path);
    }

    @Test
    public void getRegistrationPageIsOK() throws Exception {
        mockMvc.perform(get("/customer/registration"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception{
        mockMvc.perform(buildPostRequest("/customer/registration"))
                .andExpect(status().isOk());

    }


    private String toJson(Object object) throws JsonProcessingException{
        String str =  new ObjectMapper().writeValueAsString(object);
        return str;
    }
    private MockHttpServletRequestBuilder buildPostRequest(String path) throws JsonProcessingException {
        Customer customer = customersUtil.getRandomCustomer();
        customer.getCart().setCustomer(null);
        customer.getUser().setCustomer(null);
        customer.getShippingAddress().setCustomer(null);
        customer.getBillingAddress().setCustomer(null);
        return post(path).content(toJson(customer)).contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8);
    }
}