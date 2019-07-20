package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.config.ApplicationConfig;
import com.springboot.project.onlineShop.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CustomerService todoServiceMock;

    @Test
    public void sayHello(){
        System.out.println("Hello");
    }
}