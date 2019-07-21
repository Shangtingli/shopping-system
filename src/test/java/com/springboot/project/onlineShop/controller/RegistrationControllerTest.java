package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.config.ApplicationConfig;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.CustomerBuilder.CustomerBuilder;
import com.springboot.project.onlineShop.model.CustomerBuilder.CustomerBuilderFactory;
import com.springboot.project.onlineShop.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class})
@WebAppConfiguration
public class RegistrationControllerTest {

    private static final String FIRST_NAME = "Shangting";
    private static final String LAST_NAME = "Li";
    private static final String PHONE = "2174024361";
    private static final String EMAIL = "shangtingli@outlook.com";
    private static final String PASSWORD= "1234";

    //Assumes shipping address is same as billing address
    private static final String ADDRESS= "2595 South Hoover Street, Apt 111";
    private static final String CITY = "Los Angeles";
    private static final String STATE = "California";
    private static final String ZIPCODE = "90007";
    private static final String COUNTRY = "United States";

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    private MockHttpServletRequestBuilder buildGetRequest(String path){
        return mockMvc.perform(get(path))
                .andExpect(status())
    }
//    private MockHttpServletRequestBuilder buildPostRequest(){
//
//    }
//    private CustomerBuilder getCustomerBuilder(){
//        CustomerBuilder builder = CustomerBuilderFactory.getBuilder();
//        builder.setFirst_name(FIRST_NAME);
//        builder.setLast_name(LAST_NAME);
//        builder.setAddress(ADDRESS);
//        builder.setCity(CITY);
//        builder.setCountry(COUNTRY);
//        builder.setCustomer_email(EMAIL);
//        builder.setCustomer_phone(PHONE);
//        builder.setPassword(PASSWORD);
//        builder.setState(STATE);
//        builder.setZipcode(ZIPCODE);
//        return builder;
//    }
//    private MockHttpServletRequestBuilder buildPostRequest(String firstName, String lastName, String email,)
}